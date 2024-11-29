import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.ArrayList;

class Trabajo {
    private ArrayList<String> tareasPendientes= new ArrayList<>();
    private int tamaño =100;
    private int limite_quiebra =250;
    private int objetivo = 5000;
    private int picoMaximo =0;
    private boolean quebrado = false;
    private boolean objetivo_alcanzado = false;
    private int tareas_realizadas=0;

    public synchronized void insertarTarea(String tarea){
        if (tareasPendientes.size()<limite_quiebra){
            tareasPendientes.add(tarea);
        }else {
            quebrado = true;
        }
    }

    public synchronized void hacerTarea(){

        if(!tareasPendientes.isEmpty()){
            String tarea = tareasPendientes.remove(0);
            tareasPendientes.remove(0);
            System.out.println("Tarea " + tarea + " realizada por " + Thread.currentThread().getName());
            tareas_realizadas++;
            if(tareas_realizadas==objetivo){
                objetivo_alcanzado = true;
            }
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getLocalizedMessage());
            }

        }
    }

    public ArrayList<String> getTareasPendientes() {
        return tareasPendientes;
    }


    public int getTareas_realizadas() {
        return tareas_realizadas;
    }

    public int getObjetivo() {
        return objetivo;
    }
}







