import java.util.List;

public class Empaquetador extends Thread {
    private Almacen almacen;

    public Empaquetador(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        int numeroLote = 1;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                List<Saco> sacos = almacen.retirarSacos(10);
                int pesoTotal = sacos.stream().mapToInt(Saco::getPeso).sum();
                int pesoMax = sacos.stream().mapToInt(Saco::getPeso).max().orElse(0);
                int pesoMin = sacos.stream().mapToInt(Saco::getPeso).min().orElse(0);
                double pesoPromedio = pesoTotal / 10.0;

                System.out.println("Lote " + numeroLote + ":");
                System.out.println("Sacos: " + sacos.size());
                System.out.println("Peso Total: " + pesoTotal + " KG");
                System.out.println("Peso Promedio: " + pesoPromedio + " KG");
                System.out.println("Peso Máximo: " + pesoMax + " KG");
                System.out.println("Peso Mínimo: " + pesoMin + " KG\n");

                numeroLote++;
                Thread.sleep(500);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                break;

            }
        }
    }
}