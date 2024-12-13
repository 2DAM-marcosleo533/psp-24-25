public class Granja extends Thread {
    //Definimos las variables
    private final Secadero secadero;
    private final String nombre;
    private final int numJamones;
    private final int tiempoProduccion;
    private static int contJamones = 0;

    //Constructor de la clase granja
    public Granja(Secadero secadero, String nombre, int numJamones, int tiempoProduccion)
    { this.secadero = secadero;
        this.nombre = nombre;
        this.numJamones = numJamones;
        this.tiempoProduccion = tiempoProduccion;
    }

    //Metodorun donde crearemos los jamones con los hilos
    @Override
    public void run() {
        try {
            for (int i = 0; i < numJamones; i++) {
                Jamon jamon = new Jamon(contJamones++, nombre, 6 + Math.random() * 3);
            while (!secadero.agregarJamon(jamon))
            {
                Thread.sleep(10);
            }
            //Usaremos el tiempo de produccion de cada granja
            Thread.sleep(tiempoProduccion);
            }
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}