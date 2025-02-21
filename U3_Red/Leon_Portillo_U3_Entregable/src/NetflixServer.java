import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class NetflixServer {
    private static final int PUERTO = 6000;
    static final Map<String, String> usuarios = new HashMap<>();
    static final Map<String, Boolean> cartelera = new HashMap<>();

    static {
        usuarios.put("ADMIN", "ADMIN");
        usuarios.put("NOMBRE", "USER");
    }

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor en ejecución...");

            while (true) {
                try {
                    Socket socket = servidor.accept();
                    new Thread(new ClientHandler(socket)).start();
                } catch (IOException e) {
                    System.err.println("Error al aceptar la conexión: " + e.getLocalizedMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getLocalizedMessage());
        }
    }
}