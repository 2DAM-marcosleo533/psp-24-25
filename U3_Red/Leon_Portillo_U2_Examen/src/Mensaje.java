
import java.io.Serializable;
import java.time.LocalDateTime;

public class Mensaje implements Serializable {
    private String remitente;
    private String destinatario;
    private String contenido;
    private LocalDateTime fechaHora;
    private boolean leido;

    public Mensaje(String remitente, String destinatario, String contenido, LocalDateTime fechaHora, boolean leido) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.contenido = contenido;
        this.fechaHora = fechaHora;
        this.leido = leido;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}