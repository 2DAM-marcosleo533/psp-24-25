import java.io.*;
import java.util.Locale;

public class FileSizeCalculator {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Insertar ruta a directorio o archivo");
            return;
        }

        String ruta = args[0];
        File file = new File(ruta);
        String comando = "";

        if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win")) {
            if (file.isDirectory()) {
                comando = "cmd /c dir /s \"" + ruta + "\"";
            } else {
                comando = "cmd /c dir \"" + ruta + "\"";
            }
        } else {
            if (file.isDirectory()) {
                comando = "du -sb \"" + ruta + "\"";
            } else {
                comando = "du -b \"" + ruta + "\"";
            }
        }

        try {

            ProcessBuilder pb = new ProcessBuilder(comando.split(" "));
            Process proceso = pb.start();


            long tamanoTotal = obtenerTamanoTotal(proceso);


            guardarResultado(ruta, tamanoTotal);

        } catch (IOException e) {
            System.out.println("Error al ejecutar el comando: " + e.getMessage());
        }
    }

    private static long obtenerTamanoTotal(Process p) {
        long tamanoTotal = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String linea;
            if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win")) {

                while ((linea = reader.readLine()) != null) {

                    if (linea.toLowerCase().contains("bytes")) {

                        String[] partes = linea.trim().split("\\s+");
                        for (String parte : partes) {

                            try {
                                tamanoTotal = Long.parseLong(parte.replace(",", "").replace(".", ""));
                                break;
                            } catch (NumberFormatException e) {

                            }
                        }
                    }
                }
            } else {

                if ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split("\\s+");
                    tamanoTotal = Long.parseLong(partes[0]);
                }
            }

            p.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al leer la salida del proceso: " + e.getMessage());
        }

        return tamanoTotal;
    }

    private static void guardarResultado(String ruta, long tamanoTotal) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resultado.txt", true))) {
            String tipo = new File(ruta).isDirectory() ? "directorio" : "archivo";
            writer.write("El " + tipo + " " + ruta + " tiene un tamaño total de " + tamanoTotal + " bytes");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo Resultado.txt: " + e.getMessage());
        }
    }
}
