import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Almacen {
    private Stack<Saco> pila = new Stack<>();
    private final int capacidad = 500;

    public synchronized void agregarSaco(Saco saco) throws InterruptedException {
        while (pila.size() >= capacidad) {
            wait();
        }
        pila.push(saco);
        notifyAll();
    }

    public synchronized List<Saco> retirarSacos(int cantidad) throws InterruptedException {
        while (pila.size() < cantidad) {
            wait();
        }
        List<Saco> sacos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            sacos.add(pila.pop());
        }
        notifyAll();
        return sacos;
    }
}