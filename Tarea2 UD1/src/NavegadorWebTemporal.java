/**
 * Cree una clase NavegadorWebTemporal que cada 10 segundos lance un nuevo proceso
 * del navegador web preguntando al usuario que dirección web desea consultar.
 * */

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class NavegadorWebTemporal {

    public static void main(String[] args) {
        // Crear una instancia de NavegadorWebTemporal y lanzar el proceso cada 10 segundos
        NavegadorWebTemporal navegador = new NavegadorWebTemporal();
        navegador.lanzarNavegadorCada10Segundos();
    }

    // Método que programa la tarea de lanzar el navegador cada 10 segundos
    public void lanzarNavegadorCada10Segundos() {
        Timer timer = new Timer();
        TimerTask tareaNavegador = new TimerTask() {
            @Override
            public void run() {
                // Preguntar al usuario por la URL
                String url = preguntarURL();

                // Abrir la URL en el navegador web predeterminado
                if (url != null && !url.isEmpty()) {
                    abrirNavegador(url);
                }
            }
        };

        // Programar la tarea para que se ejecute cada 10 segundos (10000 milisegundos)
        timer.schedule(tareaNavegador, 0, 10000);
    }

    // Método para preguntar al usuario por una URL a través de la consola
    private String preguntarURL() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la dirección web que desea consultar: ");
        return scanner.nextLine();
    }

    // Método para abrir el navegador con la URL dada
    private void abrirNavegador(String url) {
        if (Desktop.isDesktopSupported()) {
            try {
                // Convertir la URL en un objeto URI y abrirla en el navegador predeterminado
                URI uri = new URI(url);
                Desktop.getDesktop().browse(uri);
                System.out.println("Navegador abierto con la URL: " + url);
            } catch (IOException | URISyntaxException e) {
                System.out.println("Error al intentar abrir el navegador: " + e.getMessage());
            }
        } else {
            System.out.println("Abrir el navegador no está soportado en este sistema.");
        }
    }
}