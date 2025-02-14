import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String destinatarioPreferido;
    private Long idConexion;

    public Usuario(String nombre, String destinatarioPreferido, Long idConexion) {
        this.nombre = nombre;
        this.destinatarioPreferido = destinatarioPreferido;
        this.idConexion = idConexion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDestinatarioPreferido() {
        return destinatarioPreferido;
    }

    public Long getIdConexion() {
        return idConexion;
    }
}


