import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteRomantico {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 6969;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             ObjectOutputStream sal = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ent = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Introduzca su nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Introduzca el nombre del destinatario: ");
            String destinatarioPreferido = scanner.nextLine();
            Usuario usuario = new Usuario(nombre, destinatarioPreferido, System.currentTimeMillis());
            sal.writeObject(usuario);

            while (true) {
                System.out.println("1) Enviar mensaje");
                System.out.println("2) Ver respuestas recibidas");
                System.out.println("3) Cambiar destinatario");
                System.out.println("4) SAlir");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Introduzca el mensaje: ");
                        String mensaje = scanner.nextLine();
                        sal.writeObject(new Comando(Comando.Tipo.SEND, destinatarioPreferido + ":" + mensaje));
                        break;
                    case 2:
                        sal.writeObject(new Comando(Comando.Tipo.VIEW, ""));
                        break;
                    case 3:
                        System.out.print("Introduzca el nuevo destinatario: ");
                        destinatarioPreferido = scanner.nextLine();
                        sal.writeObject(new Comando(Comando.Tipo.CHANGE, destinatarioPreferido));
                        break;
                    case 4:
                        sal.writeObject(new Comando(Comando.Tipo.EXIT, ""));
                        return;
                }

                Respuesta respuesta = (Respuesta) ent.readObject();
                System.out.println("Respuesta del servidor: " + respuesta.getNotificacion());
                if (respuesta.getTipo() == Respuesta.Tipo.LISTA_MENSAJES) {
                    for (Mensaje msg : respuesta.getMensajes()) {
                        System.out.println(msg.getContenido());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}