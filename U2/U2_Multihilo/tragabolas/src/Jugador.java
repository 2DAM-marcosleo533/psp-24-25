public class Jugador {
    private final int id;
    private final Hipopotamo hipopotamo;

    public Jugador(int id, Hipopotamo hipopotamo) {
        this.id = id;
        this.hipopotamo = hipopotamo;
    }

    public int getId() {
        return id;
    }

    public Hipopotamo getHipopotamo() {
        return hipopotamo;
    }
}
