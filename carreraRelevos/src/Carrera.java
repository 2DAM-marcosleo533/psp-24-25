public class Carrera {
    private final int totalCorredores;

    public Carrera(int totalCorredores) {
        this.totalCorredores = totalCorredores;
    }

    public void iniciarCarrera() {
        Corredor siguiente = null;
        for (int i = totalCorredores; i >= 1; i--) {
            siguiente = new Corredor(i, siguiente);
        }

        System.out.println("Todos los " + totalCorredores + " hilos creados.");
        if (siguiente != null) {
            siguiente.start();
        }
    }

    public static void main(String[] args) {
        Carrera carrera = new Carrera(4);
        carrera.iniciarCarrera();
    }
}