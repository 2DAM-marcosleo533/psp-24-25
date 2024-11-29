public class fabricaYesos{
    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        Productor productor = new Productor(almacen);
        Empaquetador empaquetador = new Empaquetador(almacen);

        productor.start();
        empaquetador.start();

        try {
            productor.join();
            empaquetador.interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}