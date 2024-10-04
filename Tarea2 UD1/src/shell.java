/**
 * Cree una clase Shell que a partir de línea de comandos tome un parámetro de entrada:
 * Si no se pasa algún parámetro visualizar un mensaje de error:
 * Si el parámetro es un fichero visualizar el contenido del fichero con el comando adecuado
 * (en Windows/Linux: more <nombrefichero>):
 * >java Shell fichero.txt
 * Si el parámetro es un directorio visualizar sus ficheros o subdirectorios según
 * (considerar el tratamiento para un único SO p.e. en Windows:
 * dir <directorio> y en Linux: ls <directorio>):
 * >java Shell directorio
 * */

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class shell {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No se ha pasado ningún parámetro.");
            return;
        }

        // Obtener el parámetro pasado (archivo o directorio)
        String parametro = args[0];
        File file = new File(parametro);

        // Verificar si es un archivo
        if (file.isFile()) {
            System.out.println("Visualizando el contenido del fichero: " + parametro);
            ejecutarComando("more", parametro); // Usar "cat" en lugar de "more" para Linux
        }
        // Verificar si es un directorio
        else if (file.isDirectory()) {
            System.out.println("Listando el contenido del directorio: " + parametro);
            ejecutarComando("dir", parametro);
        }
        // En caso de que no sea ni archivo ni directorio
        else {
            System.out.println("Error: El parámetro no es un archivo ni un directorio válido.");
        }
    }

    // Método que ejecuta un comando del sistema
    private static void ejecutarComando(String comando, String argumento) {
        ProcessBuilder builder = new ProcessBuilder();
        // Asignar el comando y el parámetro al ProcessBuilder
        builder.command(comando, argumento);

        try {
            // Iniciar el proceso
            Process process = builder.start();

            // Obtener la salida del proceso y redirigirla a la consola
            process.getInputStream().transferTo(System.out);

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            System.out.println("\nEl comando terminó con código de salida: " + exitCode);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al ejecutar el comando: " + e.getMessage());
        }
    }
}