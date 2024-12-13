public class Cubo {
    //Definimos las variables
    private int botellines = 0;
    private final int capacidad = 5;

    //Metodo para cada vez que un alumno beba un botellin
    public synchronized void beberBotellin() throws InterruptedException {
        //Si no hay botellines,no se podra beber
        while (botellines == 0) {
            wait();
        }
        botellines--;
        System.out.println("");
        System.out.println("Un alumno ha bebido del botellin");
        System.out.println("Botellines restantes: " + botellines);
        notifyAll();
    }

    //Metodo para que el Camarero rellene el cubo
    public synchronized void rellenarCubo() throws InterruptedException {
        //Si esta lleno, no se podrá rellenar
        while (botellines == capacidad) {
            wait();
        }
        botellines=5;
        System.out.println("");
        System.out.println("Un camarero ha rellenano el cubo ");
        System.out.println("Botellines actuales: " + botellines);
        notifyAll();
    }
}
