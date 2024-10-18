public class Plato {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Introduzca un plato.");
            return;
        }

        String plato = args[0];
        switch (plato.toLowerCase()) {
            case "gazpacho":
                prepararGazpacho();
                break;
            case "salmorejo":
                prepararSalmorejo();
                break;
            case "pescaíto":
                prepararPescaitoFrito();
                break;
            case "tortillita":
                prepararTortillitaDeCamarones();
                break;
            default:
                System.out.println("Este plato no está en el menú: " + plato);
        }
    }

    private static void prepararGazpacho() {
        System.out.println("Preparación del Gazpacho:");
        System.out.println("1. Lavar y cortar los tomates.");
        System.out.println("2. Añadir pepino, pimiento, ajo y sal.");
        System.out.println("3. Remover todo hasta que esté líquido.");
        System.out.println("4. Disfruta!!!");
    }

    private static void prepararSalmorejo() {
        System.out.println("Preparación del Salmorejo:");
        System.out.println("1. Triturar tomates y pan remojado.");
        System.out.println("2. Añadir ajo, aceite de oliva y sal.");
        System.out.println("3. Triturar hasta obtener crema.");
        System.out.println("4. Disfruta!!!");
    }

    private static void prepararPescaitoFrito() {
        System.out.println("Preparación del Pescaíto Frito:");
        System.out.println("1. Limpiar y cortar el pescado.");
        System.out.println("2. Echar harina.");
        System.out.println("3. Freír en aceite bien caliente.");
        System.out.println("4. Disfruta!!!");
    }

    private static void prepararTortillitaDeCamarones() {
        System.out.println("Preparación de la Tortillita de Camarones:");
        System.out.println("1. Mezclar harina de trigo y de garbanzo.");
        System.out.println("2. Añadir camarones crudos a la mezcla.");
        System.out.println("3. Freír pequeñas porciones en aceite caliente.");
        System.out.println("4. Disfruta!!!");
    }
}
