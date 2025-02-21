import java.io.Serializable;

public class Login implements Serializable {
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    private String usuario;
    private String contrasena;


        public Login(String usuario, String contrasena) {
            this.usuario = usuario;
            this.contrasena = contrasena;
        }
}
