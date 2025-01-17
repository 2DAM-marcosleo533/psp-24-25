import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Servidor {

    private static final int PUERTO = 5555;

    public static void main(String[] args) {
        int intentosMax = 10;
        boolean acertado = false;
        ArrayList<Jugador> listaJugadores = new ArrayList<>(); //ALMACENO TODOS LOS JUGADORES QUE HAN ACCEDIDO
        try {
            Random rd = new Random();
            int numSecreto = rd.nextInt();
            System.out.println("El numero secreto es: " + numSecreto);

            ServerSocket server = new ServerSocket(PUERTO);
            System.out.println("Escuchando por el puerto"+PUERTO);

            //CREAMOS EL SOCKET DEL CLIENTE
            Socket clienteSocket = server.accept();

            //CREAMOS EL FLUJO DE ENTRADA (MENSAJES)
            DataOutputStream flujoSalidaMensajes = new DataOutputStream(clienteSocket.getOutputStream());

            //CREAR FUJO DE SALIDA (OBJETOS)
            ObjectOutputStream flujoSalidaObjetos = new ObjectOutputStream((clienteSocket.getOutputStream()));

            //CREAR FLUJODE ENTRADA DE MENSAJES DEL CLIENTE
            DataInputStream flujoEntradaMensajes = new DataInputStream(clienteSocket.getInputStream());

            //1ª COMPROBAR SI YA EXISTE EL JUGADOR
            String nombreJugador = flujoEntradaMensajes.readUTF();
            Jugador j = new Jugador(nombreJugador, 0);
            boolean existe = false;
            for (Jugador pjAux:listaJugadores){
                if (pjAux.getNombre().equals(j.getNombre())){
                    j=pjAux;
                }
            }
            if (!existe){
                listaJugadores.add(j);
            }else {
                existe= false;
            }

            if (intentosMax<10 && !acertado){
                if (j.getIntentos()<3){
                    flujoSalidaObjetos.writeObject(new Acreditacion("Acceso concedido", true));
                    intentosMax++;
                    //RECOJO EL NUMERO PASADO POR EL CLIENTE
                    String numero = flujoEntradaMensajes.readUTF();

                    if (String.valueOf(numSecreto).equals(numero)){
                        System.out.println("El jugador acerto el numero");
                        flujoSalidaMensajes.writeUTF("HAS ACERTADO EL NUMERO: || NUMERO INTENTOS: " +intentosMax);
                        acertado= true;
                    } else {
                        j.setIntentos(+1);//EN EL CASO DE QUE FALLE LE SUMO UN INTENTO
                        if (numSecreto<Integer.valueOf(numero)){
                            //flujoSalidaMensajes
                        }
                    }
                }
            } else {
                flujoSalidaObjetos.writeObject(new Acreditacion("Scceso denegado", false));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
