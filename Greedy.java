package tpe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Greedy {
    private Solucion respuesta;
    private Solucion solucionActual;
    private List<Procesador> procesadores;
    private List<Tarea> tareas;
    private int tiempoEjecucion;

    public Greedy(List<Procesador> procesadores, List<Tarea> tareas) {
        this.respuesta = new Solucion();
        this.solucionActual = new Solucion();
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.tiempoEjecucion = 0;
    }

    public Solucion asignarTareasGreedy(int tiempoEjecucion) {
        int index = 0;
        this.tiempoEjecucion = tiempoEjecucion;

        ordenarTareasCriticasYTiempo(tareas);

        // Recorremos cada tarea para asignarla a un procesador válido.
        for (Tarea tarea : tareas) {
            if (index == procesadores.size()) {
                index = 0;
            }
            while (index < procesadores.size()) {
                Procesador procesadorAsignado = procesadores.get(index);
                if (procesadorAsignado.puedeAsignarse(tarea, tiempoEjecucion)) {
                    procesadorAsignado.add(tarea);
                    solucionActual.addProcesador(procesadorAsignado);
                }
                index++;
            }


        }
        respuesta = solucionActual.copy();
        return respuesta;
    }

    private void ordenarTareasCriticasYTiempo(List<Tarea> tareas) {

    }

}