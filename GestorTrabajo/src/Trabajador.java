class Trabajador extends Thread {

    private
    Trabajo trabajo;

    public Trabajador(Trabajo trabajo, String nombre) {
        this.trabajo = trabajo;
        Thread.currentThread().setName(nombre);
    }

    @Override
    public void run() {
        while (!trabajo.isObjetivo_alcanzado()&& !trabajo.isQuebrado){
            trabajo.hacerTarea();

        }
        System.out.println(Thread.currentThread().getName() + "ha terminado");
    }
}