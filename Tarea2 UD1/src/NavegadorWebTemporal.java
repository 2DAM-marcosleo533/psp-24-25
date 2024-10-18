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

        NavegadorWebTemporal navegador = new NavegadorWebTemporal();
        navegador.lanzarNavegadorCada10Segundos();
    }


    public void lanzarNavegadorCada10Segundos() {
        Timer timer = new Timer();
        TimerTask tareaNavegador = new TimerTask() {
            @Override
            public void run() {

                String url = preguntarURL();


                if (url != null && !url.isEmpty()) {
                    abrirNavegador(url);
                }
            }
        };


        timer.schedule(tareaNavegador, 0, 10000);
    }


    private String preguntarURL() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la dirección web que desea consultar: ");
        return scanner.nextLine();
    }


    private void abrirNavegador(String url) {
        if (Desktop.isDesktopSupported()) {
            try {
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