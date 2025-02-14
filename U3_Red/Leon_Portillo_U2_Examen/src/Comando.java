import java.io.Serializable;

public class Comando implements Serializable {
    public enum Tipo {
        SEND, VIEW, CHANGE, EXIT
    }

    private Tipo tipo;
    private String contenido;

    public Comando(Tipo tipo, String contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getContenido() {
        return contenido;
    }
}