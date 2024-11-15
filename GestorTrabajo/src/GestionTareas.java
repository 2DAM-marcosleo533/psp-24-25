import java.util.ArrayList;
import java.util.List;

public class GestionTareas {
    public static void main(String[] args) {
        Trabajo trabajo = new Trabajo();
        final int numTrabajadores = 20;
        final int nJefes = 5;

        for (int i = 0; i < numTrabajadores; i++) {
            Trabajador trabajador = new Trabajador(trabajo, "Trabajador_" + i);
            trabajador.start();
        }
        for (int i = 0; i < nJefes; i++) {
            Jefe jefe = new Jefe(trabajo, "Jefe_" + i);
            Jefe.start();
        }
    }
}