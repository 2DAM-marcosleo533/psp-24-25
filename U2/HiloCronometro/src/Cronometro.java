public class Cronometro extends Thread {

    public Cronometro(String nombre) {
        super(nombre);
    }

    public void run() {

        // Obtiene un número de milisegundos
        long inicio = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            long inicio2 = System.currentTimeMillis();
            while (System.currentTimeMillis() - inicio2 <= 1000) {
                // No hacer nada
            }
            System.out.println((System.currentTimeMillis() - inicio) / 1000 + " seg.");
        }

        System.out.println("Finaliza el hilo secundario");
    }

    public static void main(String args[]) throws InterruptedException {
        Cronometro hiloCronometro = new Cronometro("Cronometro");
        hiloCronometro.start();

        hiloCronometro.join(3000);

        if (hiloCronometro.isAlive()) {
            System.out.println("¡Sigo vivo!");
            hiloCronometro.join();
        }

        System.out.println("Finaliza el hilo principal");
    }
}


/*
¿Qué ocurre al producirse hiloCronometro.start()?
Se inicializa un cronometro que cuenta los segundos del 1 al 5
¿Qué ocurriría si se anula hiloCronometro.start()?
Que finaliza el hilo principal sin contar los segundos ni decir que esta vivo
¿Qué estado tendría durante la ejecución del padre o hilo principal si se anula hiloCronometro.start()? ¿Y el hilo hijo?

¿Qué ocurriría si se usa en lugar de hiloCronometro.start() por hiloCronometro.run()?

¿En qué estado se encuentra el padre o hilo principal cuando acaba el hilo de ejecución lanzado?

¿En qué estado se encuentra el hilo tras pasar el primer join()?

¿Qué diferencias existe entre el primer y segundo join()? ¿Posee el mismo efecto y salida si se anula el primer join()?

¿En qué estado se encuentra el hilo tras pasar el segundo join()?

¿De qué manera obtendrías la siguiente salida?

*/