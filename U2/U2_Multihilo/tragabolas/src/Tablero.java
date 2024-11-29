import java.util.Collections;
import java.util.List;

public class Tablero {
    private final List<Hipopotamo> hipopotamos;

    public Tablero(List<Hipopotamo> hipopotamos) {
        this.hipopotamos = hipopotamos;
    }

    public void jugarRonda(Jugador jugador, int ronda) {
        System.out.println("Ronda " + ronda + ", lanza la bola Jugador " + jugador.getId());
        int intentos = 0;
        Hipopotamo ganador = null;

        //Mientras no haya un ganador, cada hipopotamo intentará demanera aleatoria comerse la bola
        while (ganador == null) {
            //El hipopotamo que hace el intento sera aleatorio
            Collections.shuffle(hipopotamos);

            for (Hipopotamo hipopotamo : hipopotamos) {
                intentos++;
                if (hipopotamo.intentarComerBola()) {
                    ganador = hipopotamo;
                    break;
                }
            }
        }
        //Cuando se coma uno la bola, decimos que hipopotamo se la ha comido y los intentos
        ganador.comerBola();
        System.out.println("Ronda " + ronda + ", el hipopótamo " + ganador.getId() +  " se ha comido la bola en el intento " + intentos);

    }
}
