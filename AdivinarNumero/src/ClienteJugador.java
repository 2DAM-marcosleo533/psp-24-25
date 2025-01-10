import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteJugador {

    private static final int PUERTO = 5555;
    private static final String SERVER = "localhost";
    private static int jugadas = 0;
    private static int intentosJugador=0;

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            Socket clientSocket = new Socket();
            InetSocketAddress addr = new InetSocketAddress(SERVER, PUERTO);
            clientSocket.connect(addr);

            ObjectInputStream flujoEntrada = new ObjectInputStream(clientSocket.getInputStream());

            Integer numero = (Integer) flujoEntrada.readObject();
            System.out.println("CLIENTE: Recibiendo del SERVIDOR el numero: " + numero);

            while (true){
                if (jugadas>=10){
                    System.out.println("Se han realizado ya 10 jugadas");
                    flujoEntrada.close();
                    clientSocket.close();
                }


            int numeroAd = sc.nextInt();

            if (numero == numeroAd){
                System.out.println("Número adivinado");
                flujoEntrada.close();
                clientSocket.close();
            }

            jugadas++;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("No se ha podido realizar la conversión del objeto");
        }
    }
}
