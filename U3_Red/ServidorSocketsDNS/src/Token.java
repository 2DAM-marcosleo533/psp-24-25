import java.io.Serializable;

public class Token implements Serializable {
    private long codCliente;
    private String operacion;

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public long getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    public Token(long codCliente, String operacion) {
        this.codCliente = codCliente;
        this.operacion = operacion;
    }

    @Override
    public String toString() {
        return "Token{" +
                "Codigo=" + codCliente +
                " Operacion='" + operacion + '\'' +
                '}';
    }

    public boolean isAdmind(){
        return codCliente == 9999999999L;
    }

    public boolean isUser(){
        return codCliente == 1111111111L;
    }

    public boolean isAdminREG(){
        return operacion.equals("REG");
    }

    public boolean isAdminDEL(){
        return operacion.equals("DEL");
    }
}