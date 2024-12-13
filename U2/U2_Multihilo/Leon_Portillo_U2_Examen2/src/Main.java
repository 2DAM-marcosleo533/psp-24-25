public class Main {
    public static void main(String[] args) {
        //Definimos lasvariables con sus tipos de objetos
        Cubo cubo = new Cubo();

        Camarero camarero1 = new Camarero(cubo);
        Camarero camarero2 = new Camarero(cubo);

        Alumno alumno1 = new Alumno(cubo, 10, 50);
        Alumno alumno2 = new Alumno(cubo, 8, 100);
        Alumno alumno3 = new Alumno(cubo, 12, 80);
        Alumno alumno4 = new Alumno(cubo, 6, 150);
        Alumno alumno5 = new Alumno(cubo, 10, 120);
        Alumno alumno6 = new Alumno(cubo, 9, 90);

        //Las iniciamos
        camarero1.start();
        camarero2.start();
        alumno1.start();
        alumno2.start();
        alumno3.start();
        alumno4.start();
        alumno5.start();
        alumno6.start();
    }
}