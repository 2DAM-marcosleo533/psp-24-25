import java.io.Serializable;

public class Acreditacion implements Serializable {
    public Acreditacion(String mensaje, boolean flag) {
        this.mensaje = mensaje;
        this.flag = flag;
    }

    private String mensaje;
    private boolean flag;

    @Override
    public String toString(){
        return mensaje + flag;
    }

}
