import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
             ObjectInputStream entradaObj = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream salidaObj = new ObjectOutputStream(socket.getOutputStream())) {

            Login login = (Login) entradaObj.readObject();
            String usuario = login.getUsuario();
            String contrasena = login.getContrasena();

            if (NetflixServer.usuarios.containsKey(usuario) && NetflixServer.usuarios.get(usuario).equals(contrasena)) {

                if (usuario.equals("ADMIN")) {
                    salidaObj.writeObject("Bienvenido a Netflix Administrador!!! ¿Qué acción desea realizar ALTA o BAJA??");
                    salidaObj.flush();
                    handleAdmin(entradaObj, salidaObj);
                } else {
                    salidaObj.writeObject("Bienvenido a Netflix " + usuario + " ¿Qué acción desea realizar VER o BUSCAR?");
                    salidaObj.flush();
                    handleUser(entradaObj, salidaObj, usuario);
                }
            }
            else {
                salidaObj.writeObject("Usuario y password incorrectos");
                salidaObj.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }

    private void handleAdmin(ObjectInputStream entradaObj, ObjectOutputStream salidaObj) throws IOException, ClassNotFoundException {
        String accion = (String) entradaObj.readObject();
        if ("ALTA".equalsIgnoreCase(accion)) {
            salidaObj.writeObject("Ingrese el nombre de la película a registrar:");
            salidaObj.flush();
            String pelicula = (String) entradaObj.readObject();
            if (NetflixServer.cartelera.containsKey(pelicula)) {
                salidaObj.writeObject("La película ya estaba en la cartelera");
            } else {
                NetflixServer.cartelera.put(pelicula, false);
                salidaObj.writeObject("La película se ha registrado correctamente");
            }
            salidaObj.flush();
        } else if ("BAJA".equalsIgnoreCase(accion)) {
            salidaObj.writeObject("Ingrese el nombre de la película a eliminar:");
            salidaObj.flush();
            String pelicula = (String) entradaObj.readObject();

            if (NetflixServer.cartelera.remove(pelicula) != null) {
                salidaObj.writeObject("La película se ha eliminado correctamente");
            } else {
                salidaObj.writeObject("No se ha podido eliminar la peli porque no existía");
            }
            salidaObj.flush();

        } else {
            salidaObj.writeObject("Operacion no válida");
            salidaObj.flush();
        }
    }

    private void handleUser(ObjectInputStream entradaObj, ObjectOutputStream salidaObj, String usuario) throws IOException, ClassNotFoundException {
        String accion = (String) entradaObj.readObject();
        if ("VER".equalsIgnoreCase(accion)) {
            salidaObj.writeObject("Ingrese el nombre de la película a ver:");
            salidaObj.flush();
            String pelicula = (String) entradaObj.readObject();
            if (NetflixServer.cartelera.containsKey(pelicula)) {
                NetflixServer.cartelera.put(pelicula, true);
                salidaObj.writeObject("ha seleccionado para ver la película " + pelicula);
            } else {
                salidaObj.writeObject("Error la película " + pelicula + " no está en la cartelera");
            }
            salidaObj.flush();
        } else if ("BUSCAR".equalsIgnoreCase(accion)) {
            StringBuilder listado = new StringBuilder();
            NetflixServer.cartelera.forEach((pelicula, vista) -> listado.append(pelicula).append(" → ").append(vista ? "VISTA" : "No visualizada").append("\n"));
            salidaObj.writeObject(listado.toString());
            salidaObj.flush();
        } else {
            salidaObj.writeObject("Operacion no valida");
            salidaObj.flush();
        }
    }
}
