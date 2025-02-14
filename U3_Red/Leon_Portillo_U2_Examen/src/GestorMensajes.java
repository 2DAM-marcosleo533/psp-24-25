import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GestorMensajes {
    private final Map<String, ClientHandler> usuariosConectados = new ConcurrentHashMap<>();
    private final Map<String, List<Mensaje>> mensajesPendientes = new ConcurrentHashMap<>();

    public void registrarUsuario(String nombre, ClientHandler clientHandler) {
        usuariosConectados.put(nombre, clientHandler);
    }

    public List<Mensaje> getMensajesPendientes(String destinatario) {
        return mensajesPendientes.getOrDefault(destinatario, new ArrayList<>());
    }

    public void addMensajesPendientes(String destinatario, List<Mensaje> mensajes) {
        mensajesPendientes.putIfAbsent(destinatario, new ArrayList<>());
        mensajesPendientes.get(destinatario).addAll(mensajes);
    }

    public void eliminarUsuarioMensajesPendientes(String destinatario) {
        mensajesPendientes.remove(destinatario);
    }

    public void removeOnlineUser(String nombre) {
        usuariosConectados.remove(nombre);
    }

    public ClientHandler getOnlineUser(String nombre) {
        return usuariosConectados.get(nombre);
    }

    public List<String> getOnlineUsers() {
        return new ArrayList<>(usuariosConectados.keySet());
    }
}