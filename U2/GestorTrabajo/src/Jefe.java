class Jefe extends Thread {
    private Trabajo trabajo;
    private String nombre;


    public Jefe(String nombre, Trabajo trabajo) {
        this.nombre = nombre;
        this.trabajo = trabajo;
        Thread.currentThread().setName(nombre);
    }

    @Override
    public void run() {
        int contador = 0;
        while (!trabajo.isObjetivo_alcanzado()&& !trabajo.isQuebrado){
            trabajo.insertarTarea("Tarea " + contador);

        }
        System.out.println(Thread.currentThread().getName() + "ha terminado");
    }

}