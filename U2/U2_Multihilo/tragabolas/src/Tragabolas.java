import java.util.ArrayList;
import java.util.List;

public class Tragabolas {
    public static void main(String[] args){

        // Creamos los hipopotamos
        List<Hipopotamo> hipopotamos = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            hipopotamos.add(new Hipopotamo(i));
        }

        // Creamoslos jugadores con su hipopotamo asociado
        List<Jugador> jugadores = new ArrayList<>();
        for  (int i = 1; i <= 4; i++) {
            Jugador jugador = new Jugador(i, hipopotamos.get((i - 1) % hipopotamos.size()));
            jugadores.add(jugador);
          }

        Tablero tablero = new Tablero(hipopotamos);

        // Hacemos las rondas
        for (int ronda = 1; ronda <= 4 * 5; ronda++) {

            int turnoActual = (ronda - 1) % 4;
            Jugador jugadorActual = jugadores.get(turnoActual);
            tablero.jugarRonda(jugadorActual, ronda);

        }

        // Resumen final
        System.out.println("\n======== Resumen Final ========");

        for (Hipopotamo hipopotamo : hipopotamos) {
            System.out.println("El Hipopótamo " +  hipopotamo.getId() + " se ha comido " + hipopotamo.getBolasComidas() + " bolas");
        }

        // Clasificación final
        System.out.println("\n======== Clasificación Final ========");

        hipopotamos.stream()
                .sorted((a, b) -> Integer.compare(b.getBolasComidas(), a.getBolasComidas()))
                .forEachOrdered(hipopotamo -> {
                    System.out.println("Hipopótamo " + hipopotamo.getId() + ": " + hipopotamo.getBolasComidas() + " bolas");
                });

    }
}
