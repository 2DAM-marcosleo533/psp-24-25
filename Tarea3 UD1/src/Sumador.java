import java.io.IOException;

public class Sumador {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error: Debe proporcionar exactamente 2 números como argumentos.");
            System.exit(1);
        }

        try {
            // Convertir los argumentos a enteros y realizar la suma
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);
            int resultado = num1 + num2;

            // Imprimir el resultado por la salida estándar
            System.out.println("Resultado de la suma: " + resultado);
        } catch (NumberFormatException e) {
            System.err.println("Error: Los argumentos deben ser números enteros.");
            System.exit(1);
        }
    }
}