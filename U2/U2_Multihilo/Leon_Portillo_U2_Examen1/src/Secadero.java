import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Secadero {
    //Definimos las variables
    private final Queue<Jamon> jamones = new LinkedList<>();
    private final int capacidad = 10;

    //Metodo para cada vez que se agregue un jamon al secadero
    public synchronized boolean agregarJamon(Jamon jamon) {
        //Vamos añadiendo cuando hay capacidad
        if (jamones.size() < capacidad) {
            jamones.add(jamon);
            System.out.println("Se ha añadido un jamón de la " + jamon.granja +
                    " al secadero. Peso: " + jamon.peso + " ID: " + jamon.id);
            notify();
            return true;
        }
        return false;
    }

    //metodo para obtener el obtener el lote que va a llevar el mensajero
    public synchronized List<Jamon> obtenerLote() throws InterruptedException
    {
        //Hacemos que sea de 3
        while (jamones.size() < 3) {
            wait();
        }
        List<Jamon> lote = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            lote.add(jamones.poll());
        }

            System.out.println("El mensajero lleva un lote de jamones");
        return lote;
    }
}