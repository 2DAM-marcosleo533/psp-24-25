/**Diseñe las clases: LanzadorSumador (Clase Padre) y
 Sumador (Clase Hijo) donde Padre se comunica con su proceso Hijo:

La clase Sumador deberá:

Realizar la suma de de dos números que se le pasen como parámetros
La clase LanzadorSumador deberá:

Lanzar dos procesos hijos cada uno de ellos con dos números diferentes
Esperar a que Sumador finalice
Mostrar el resultado de salida de Hijo1 por la consola.
Guardar el valor de salida de Hijo2 en el fichero SalidaHijo2.txt.**/

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class LanzadorSumador {

    public static void main(String[] args) {
        try {
            // Lanzar el primer proceso hijo con dos números (5 y 10)
            ProcessBuilder pb1 = new ProcessBuilder("java", "Sumador", "5", "10");
            Process proceso1 = pb1.start();
            String resultadoHijo1 = leerSalidaProceso(proceso1);
            System.out.println("Hijo 1 - " + resultadoHijo1);

            // Lanzar el segundo proceso hijo con dos números (7 y 14)
            ProcessBuilder pb2 = new ProcessBuilder("java", "Sumador", "7", "14");
            Process proceso2 = pb2.start();
            String resultadoHijo2 = leerSalidaProceso(proceso2);

            // Guardar el resultado del hijo 2 en un archivo
            try (FileWriter writer = new FileWriter("SalidaHijo2.txt")) {
                writer.write("Hijo 2 - " + resultadoHijo2);
                System.out.println("Resultado del Hijo 2 guardado en SalidaHijo2.txt");
            }

            // Esperar a que ambos procesos terminen
            proceso1.waitFor();
            proceso2.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para leer la salida de un proceso
    private static String leerSalidaProceso(Process proceso) throws IOException {
        StringBuilder resultado = new StringBuilder();
        try (InputStream is = proceso.getInputStream();
             Scanner scanner = new Scanner(is)) {
            while (scanner.hasNextLine()) {
                resultado.append(scanner.nextLine()).append(System.lineSeparator());
            }
        }
        return resultado.toString().trim();
    }
}