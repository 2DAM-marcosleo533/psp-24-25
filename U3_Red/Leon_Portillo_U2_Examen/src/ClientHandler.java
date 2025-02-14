import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket socket;
    private GestorMensajes gestorMensajes;
    private Usuario usuario;
    private List<Mensaje> mensajesRecibidos;

    public ClientHandler(Socket socket, GestorMensajes gestorMensajes) {
        this.socket = socket;
        this.gestorMensajes = gestorMensajes;
    }

    @Override
    public void run() {
        try (ObjectInputStream ent = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream sal = new ObjectOutputStream(socket.getOutputStream())) {

            usuario = (Usuario) ent.readObject();
            gestorMensajes.registrarUsuario(usuario.getNombre(), this);

            mensajesRecibidos = gestorMensajes.getMensajesPendientes(usuario.getNombre());
            gestorMensajes.eliminarUsuarioMensajesPendientes(usuario.getNombre());

            if (!mensajesRecibidos.isEmpty()) {
                sal.writeObject(new Respuesta(Respuesta.Tipo.NOTIFICACION, "Tienes " + mensajesRecibidos.size() + " mensaje(s) pendiente(s). Usa la opción VIEW para leerlos", null));
            }

            while (true) {
                Comando comando = (Comando) ent.readObject();
                switch (comando.getTipo()) {
                    case SEND:
                        handleSend(comando, sal);
                        break;
                    case VIEW:
                        handleView(sal);
                        break;
                    case CHANGE:
                        handleChange(comando, sal);
                        break;
                    case EXIT:
                        handleExit(sal);
                        return;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            gestorMensajes.removeOnlineUser(usuario.getNombre());
        }
    }

    private void handleSend(Comando comando, ObjectOutputStream sal) throws IOException {
        String[] parts = comando.getContenido().split(":", 2);
        if (parts.length < 2) {
            sal.writeObject(new Respuesta(Respuesta.Tipo.NOTIFICACION, "Formato de mensaje incorrecto", null));
            return;
        }
        String destinatario = parts[0];
        String contenido = parts[1];
        Mensaje mensaje = new Mensaje(usuario.getNombre(), destinatario, contenido, LocalDateTime.now(), false);
        ClientHandler destinatarioHandler = gestorMensajes.getOnlineUser(destinatario);
        if (destinatarioHandler != null) {
            destinatarioHandler.deliverMessage(mensaje);
        } else {
            gestorMensajes.addMensajesPendientes(destinatario, List.of(mensaje));
        }
        sal.writeObject(new Respuesta(Respuesta.Tipo.NOTIFICACION, "Mensaje enviado a " + destinatario, null));
    }

    private void handleView(ObjectOutputStream sal) throws IOException {
        if (mensajesRecibidos.isEmpty()) {
            sal.writeObject(new Respuesta(Respuesta.Tipo.NOTIFICACION, "No tienes mensajes", null));
        } else {
            for (Mensaje mensaje : mensajesRecibidos) {
                if (!mensaje.isLeido()) {
                    mensaje.setLeido(true);
                    ClientHandler remitenteHandler = gestorMensajes.getOnlineUser(mensaje.getRemitente());
                    if (remitenteHandler != null) {
                        remitenteHandler.deliverMessage(new Mensaje("Sistema", mensaje.getRemitente(), "Tu mensaje a " + mensaje.getDestinatario() + " fue leído", LocalDateTime.now(), true));
                    }
                }
            }
            sal.writeObject(new Respuesta(Respuesta.Tipo.LISTA_MENSAJES, null, mensajesRecibidos));
            mensajesRecibidos.clear();
        }
    }

    private void handleChange(Comando comando, ObjectOutputStream sal) throws IOException {
        usuario = new Usuario(usuario.getNombre(), comando.getContenido(), usuario.getIdConexion());
        sal.writeObject(new Respuesta(Respuesta.Tipo.NOTIFICACION, "Destinatario preferido actualizado a: " + comando.getContenido(), null));
    }

    private void handleExit(ObjectOutputStream sal) throws IOException {
        sal.writeObject(new Respuesta(Respuesta.Tipo.NOTIFICACION, "Desconectando...", null));
    }

    public void deliverMessage(Mensaje mensaje) {
        mensajesRecibidos.add(mensaje);
    }
}