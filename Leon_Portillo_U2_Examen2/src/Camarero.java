public class Camarero extends Thread {
    //Definimos las variables
    private final Cubo cubo;

    //Constructor de la clase Camarero
    public Camarero(Cubo cubo) {
        this.cubo = cubo;
    }

    //Metodo run. El camarero rellena el cubo, con un tiempo de espera de 10 ms
    @Override
    public void run() {
        try {
            while (true) {
                cubo.rellenarCubo();
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
