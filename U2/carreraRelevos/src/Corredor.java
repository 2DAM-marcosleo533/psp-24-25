import java.util.Random;

class Corredor extends Thread {
    private final int numeroCorredor;
    private final Corredor siguienteCorredor;
    private static final Random random = new Random();

    public Corredor(int numeroCorredor, Corredor siguienteCorredor) {
        this.numeroCorredor = numeroCorredor;
        this.siguienteCorredor = siguienteCorredor;
    }

    @Override
    public void run() {
        try {

            int tiempoCorrer = random.nextInt(11);
            System.out.println("Soy el hilo " + numeroCorredor + ", corriendo durante " + tiempoCorrer + " segundos...");
            Thread.sleep(tiempoCorrer * 1000);
            System.out.println("Terminé. Paso el testigo al siguiente hilo.");


            if (siguienteCorredor != null) {
                siguienteCorredor.start();
            } else {
                System.out.println("Todos los hilos han terminado.");
            }
        } catch (InterruptedException e) {
            System.out.println("El hilo " + numeroCorredor + " fue interrumpido.");
        }
    }
}


