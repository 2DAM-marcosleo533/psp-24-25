import java.io.*;

public class Restaurante {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Introduzca un plato (gazpacho, salmorejo, pescaíto, tortillita)");
            return;
        }

        String plato = args[0];
        String nombreArchivo = null;
        boolean enArchivo = false;

        if (args.length > 1 && args[1].equals("-f") && args.length > 2) {
            nombreArchivo = args[2];
            enArchivo = true;
        }

        try {
            prepararPlato(plato, enArchivo, nombreArchivo);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error en la preparación del plato: " + e.getMessage());
        }
    }


    private static void prepararPlato(String plato, boolean outputToFile, String fileName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("java", "Plato", plato);

        if (outputToFile && fileName != null) {
            File file = new File(fileName);
            processBuilder.redirectOutput(file);
        } else {
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        }

        Process process = processBuilder.start();

        process.waitFor();
    }
}

