import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {

    private static final int PUERTO = 5555;

    private static int aleatorio() {
        Random r = new Random();
        return r.nextInt(1 - 100 + 1);
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);

            while (true) {
                System.out.println("SERVIDOR: Escuchando por el puerto " + PUERTO + " ...");
                Socket clientSocket = serverSocket.accept();

                ObjectOutputStream flujoSalida = new ObjectOutputStream(clientSocket.getOutputStream());

                Integer numero_aleatorio = aleatorio();
                flujoSalida.writeObject(numero_aleatorio);

                flujoSalida.close();
                clientSocket.close();
            }

            //Cierre de conexión principal del servidor
            // serverSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
