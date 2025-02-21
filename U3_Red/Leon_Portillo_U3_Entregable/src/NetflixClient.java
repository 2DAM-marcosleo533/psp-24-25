import java.io.*;
import java.net.Socket;

public class NetflixClient {
    public static void main(String[] args) {
        try(

             Socket socket = new Socket("localhost", 6000);
             ObjectOutputStream salidaObj = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entradaObj = new ObjectInputStream(socket.getInputStream())) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Introduzca el nombre de usuario: ");
            String usuario = br.readLine();
            System.out.print("Introduzca la contraseña: ");
            String contrasena = br.readLine();

            Login login = new Login(usuario, contrasena);
            salidaObj.writeObject(login);
            salidaObj.flush();

            String respuesta = (String) entradaObj.readObject();
            System.out.println(respuesta);

            if (respuesta.startsWith("Bienvenido")) {
                String accion = br.readLine();
                salidaObj.writeObject(accion);
                salidaObj.flush();

                respuesta = (String) entradaObj.readObject();
                System.out.println(respuesta);

                if (respuesta.contains("Ingrese el nombre de la película")) {
                    String pelicula = br.readLine();
                    salidaObj.writeObject(pelicula);
                    salidaObj.flush();

                     respuesta = (String) entradaObj.readObject();
                    System.out.println(respuesta);
                }
            }
            else {
                System.out.println("Intento de login fallido.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el cliente: " + e.getLocalizedMessage());
        }
    }
}