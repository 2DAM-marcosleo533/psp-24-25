import java.io.Serializable;
import java.util.List;

public class Respuesta implements Serializable {
    public enum Tipo {
        NOTIFICACION, LISTA_MENSAJES
    }

    private Tipo tipo;
    private String notificacion;
    private List<Mensaje> mensajes;

    public Respuesta(Tipo tipo, String notificacion, List<Mensaje> mensajes) {
        this.tipo = tipo;
        this.notificacion = notificacion;
        this.mensajes = mensajes;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

}
