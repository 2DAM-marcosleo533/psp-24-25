public class Alumno extends Thread {
    //Definimos las variables
    private final Cubo cubo;
    private final int numBotellines;
    private final int tEspera;

    //Constructor de la clase alumnos
    public Alumno(Cubo cubo, int numBotellines, int tEspera) {
        this.cubo = cubo;
        this.numBotellines = numBotellines;
        this.tEspera = tEspera;
    }

    // Metodo run,donde usaremos beberBotellin,cada vez que un alumno beba un botellin
    @Override
    public void run() {
        try {
            for (int i = 0; i < numBotellines; i++) {
                cubo.beberBotellin();
                System.out.println(Thread.currentThread().getName() + " ha bebido " + (i + 1) + " botellines.");
                //Cada botellin, va aumentando el tEspera * i+1
                Thread.sleep(tEspera * (i + 1));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}