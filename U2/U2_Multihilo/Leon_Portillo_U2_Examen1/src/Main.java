public class Main {
        public static void main(String[] args) {
            //Creamos las variables de sus tipos de objetos
            Secadero secadero = new Secadero();
            Granja granja1 = new Granja(secadero, "Granja 1", 30, 150);
            Granja granja2 = new Granja(secadero, "Granja 2", 30, 100);
            Mensajero mensajero = new Mensajero(secadero);

            //Las iniciamos
            granja1.start();
            granja2.start();
            mensajero.start();
        }


    }

