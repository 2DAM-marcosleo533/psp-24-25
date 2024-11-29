import java.util.Random;

public class Hipopotamo {
    private final int id;
    private int bolasComidas = 0;
    private final Random random = new Random();

    public Hipopotamo(int id) {
        this.id = id;
    }

    //Lo hacemos para que tenga el 20% de probabilidad de comersela en un intento
    public boolean intentarComerBola() {
        return random.nextDouble() < 0.2;
    }

    public void comerBola() {
        bolasComidas++;
    }

    public int getBolasComidas() {
        return bolasComidas;
    }

    public int getId() {
        return id;
    }
}
