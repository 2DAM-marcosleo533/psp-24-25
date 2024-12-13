import java.util.List;

public class Mensajero extends Thread {
    //Definimos las variables
    private final Secadero secadero;

    //Constructor de la clase Mensajero
    public Mensajero(Secadero secadero) {
        this.secadero = secadero;
    }

    //Metodo run, que usando hilos, cada 300 ms entrega un lote
    @Override public void run()
    {
        try
    {
        while (true)
        {
            List<Jamon> lote = secadero.obtenerLote();
            //Hacemos que el lote sea de 3
            if (lote.size() == 3)
            {
                System.out.println("Lote entregado");
                System.out.println("");
                System.out.println("=================================================");
                System.out.println("");
            }
            else
            {
                break;
            }
            Thread.sleep(300);
        }
    } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
