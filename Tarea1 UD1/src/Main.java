/**
 * Realiza un programa en Java que lance procesos del sistema. Como argumentos recibirá 3 parámetros de entrada.
 * Los posibles valores de los parámetros deberán ser siguientes:
 * Primer parámetro (obligatorio)
 * "P" (indicará que se lanzará el proceso mediante la clase ProcessBuilder)
 * "R" (indicará que se lanzará el proceso mediante la clase RunTime)
 * Segundo parámetro (obligatorio)
 * "C" --> lanzará el proceso del navegador Chrome
 * "N" --> lanzará el bloc de notas
 * Tercer parámetro (opcional)
 * Recibirá la ruta del fichero que se desea abrir con la aplicación o la url de la página que se desea abrir.
 * Si la opción escogida en el segundo parámetro es "N", el programa deberá controlar el error
 * correspondiente y si el fichero no existe debe devolver un error indicándolo.
 * El programa deberá tener un control de errores de modo que si no se pasa el número de
 * argumentos correctos, los parámetros no son válidos o salta alguna excepción los
 * mensajes sean lo más claro posibles
 */

import java.lang.ProcessBuilder;
import java.lang.Runtime;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String metodoEjecucion = args[0];
        String aplicacion = args[1];

        String ruta = args.length > 2 ? args[2] : null;

        try {
            if (metodoEjecucion.equals("P")) {

                if (aplicacion.equals("C")) {
                    lanzarChromeConProcessBuilder(ruta);
                } else if (aplicacion.equals("N")) {
                    lanzarNotepadConProcessBuilder(ruta);
                }
            } else if (metodoEjecucion.equals("R")) {

                if (aplicacion.equals("C")) {
                    lanzarChromeConRuntime(ruta);
                } else if (aplicacion.equals("N")) {
                    lanzarNotepadConRuntime(ruta);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: No se pudo lanzar el proceso. Detalles: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private static void lanzarChromeConProcessBuilder(String url) throws IOException {
        String chromePath = "C:\\Users\\2DAM-marcosleo533\\Desktop\\GoogleChrome.exe";
        ProcessBuilder pb;
        if (url != null) {
            pb = new ProcessBuilder(chromePath, url);
        } else {
            pb = new ProcessBuilder(chromePath);
        }
        pb.start();
        System.out.println("Chrome lanzado con ProcessBuilder.");
    }


    private static void lanzarNotepadConProcessBuilder(String filePath) throws IOException {
        ProcessBuilder pb;
        if (filePath != null) {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("El archivo especificado no existe.");
            }
            pb = new ProcessBuilder("notepad", filePath);
        } else {
            pb = new ProcessBuilder("notepad");
        }
        pb.start();
        System.out.println("Notepad lanzado con ProcessBuilder.");
    }


    private static void lanzarChromeConRuntime(String url) throws IOException {
        String chromePath = "C:\\Users\\TuUsuario\\Desktop\\GoogleChrome.exe";
        Runtime rt = Runtime.getRuntime();
        if (url != null) {
            rt.exec(new String[]{chromePath, url});
        } else {
            rt.exec(chromePath);
        }
        System.out.println("Chrome lanzado con Runtime.");
    }


    private static void lanzarNotepadConRuntime(String filePath) throws IOException {
        Runtime rt = Runtime.getRuntime();
        if (filePath != null) {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("El archivo especificado no existe.");
            }
            rt.exec(new String[]{"notepad", filePath});
        } else {
            rt.exec("notepad");
        }
        System.out.println("Notepad lanzado con Runtime.");
    }
}
