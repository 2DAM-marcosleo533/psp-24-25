import java.text.SimpleDateFormat;
import java.util.Date;

public class Saco {
    private String codigo;
    private int peso;
    private String fechaProduccion;

    public Saco(String codigo, int peso) {
        this.codigo = codigo;
        this.peso = peso;
        this.fechaProduccion = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public String getCodigo() {
        return codigo;
    }

    public int getPeso() {
        return peso;
    }

    public String getFechaProduccion() {
        return fechaProduccion;
    }

    @Override
    public String toString() {
        return "Saco{" +
                "codigo='" + codigo + '\'' +
                ", peso=" + peso +
                ", fechaProduccion='" + fechaProduccion + '\'' +
                '}';
    }
}