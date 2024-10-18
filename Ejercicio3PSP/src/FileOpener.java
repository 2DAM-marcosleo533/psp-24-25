import java.io.File;
import java.io.IOException;

public class FileOpener {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Introduzca la ruta a algun archivo o archivos");
            return;
        }

        for (String archivo : args) {
            String extension = obtenerExtension(archivo);
            String rutaAplicacion = determinarAplicacion(extension);

            if (rutaAplicacion != null) {
                try {
                    abrirArchivo(rutaAplicacion, archivo);
                    System.out.println("Abriendo " + archivo + " con " + rutaAplicacion);
                } catch (IOException e) {
                    System.out.println("Error al intentar abrir el archivo: " + archivo + " - " + e.getMessage());
                }
            } else {
                System.out.println("No se encontró una aplicación para abrir archivos con extensión: " + extension);
            }
        }
    }


    private static String determinarAplicacion(String extension) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            switch (extension) {
                case "txt":
                    return "notepad";
                case "pdf":
                    return "C:\\Program Files\\Adobe\\Acrobat DC\\Acrobat\\Acrobat.exe";
                case "jpg":
                case "png":
                    return "C:\\Program Files\\Windows Photo Viewer\\PhotoViewer.dll";
                default:
                    return null;
            }
        } else if (os.contains("nux") || os.contains("mac")) {
            switch (extension) {
                case "txt":
                    return "gedit";
                case "pdf":
                    return "evince";
                case "jpg":
                case "png":
                    return "xdg-open";
                default:
                    return null;
            }
        }

        return null;
    }


    private static String obtenerExtension(String archivo) {
        int index = archivo.lastIndexOf('.');
        if (index > 0 && index < archivo.length() - 1) {
            return archivo.substring(index + 1).toLowerCase();
        }
        return "";
    }


    public static Process abrirArchivo(String rutaAplicacion, String archivo) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(rutaAplicacion, archivo);
        pb.directory(new File(System.getProperty("user.dir")));
        return pb.start();
    }
}
