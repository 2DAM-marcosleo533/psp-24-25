import java.util.concurrent.ThreadLocalRandom;

public class Productor extends Thread {
    private Almacen almacen;

    public Productor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 500; i++) {
            try {
                int peso = ThreadLocalRandom.current().nextInt(25, 51);
                Saco saco = new Saco("SACO-" + i, peso);
                almacen.agregarSaco(saco);
                Thread.sleep(peso * 2L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}