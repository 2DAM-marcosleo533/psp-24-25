import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PORT = 6969;
    private static GestorMensajes gestorMensajes = new GestorMensajes();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor escuchando en el puerto " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket, gestorMensajes)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}