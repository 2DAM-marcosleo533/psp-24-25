import java.io.Serializable;

public class Jugador implements Serializable {
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String nombre;

    public int getIntentos() {
        return nIntento;
    }

    public void setIntentos(int nIntento) {
        this.nIntento = nIntento;
    }

    private int nIntento;

    public Jugador(String nombre, int nIntento) {
        this.nombre = nombre;
        this.nIntento = nIntento;
    }




}
