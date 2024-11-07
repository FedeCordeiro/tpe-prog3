package tpe;

import java.util.ArrayList;
import java.util.List;

public class Greedy {
    private Solucion respuesta;
    private Solucion solucionActual;
    private List<Procesador> procesadores;
    private List<Tarea> tareas;
    private int tiempoEjecucion;

    public Greedy(List<Procesador> procesadores, List<Tarea> Critica, List<Tarea> Nocritica) {
        this.respuesta = new Solucion();
        this.procesadores = procesadores;
        this.solucionActual = new Solucion(this.procesadores, 0);
        this.tareas = ordenarTareasPorTiempo(Critica, Nocritica);
        this.tiempoEjecucion = 0;
    }

    /**
     * Estrategia Greedy (Algoritmo Voraz):
     * Ordena las tareas en función de su tiempo de ejecución, de forma descendente (de mayor a menor).
     * Recorre las tareas en orden y busca un procesador adecuado.
     * Las tareas se asignan al procesador que tiene la menor carga si no se puede cumplir tiempoMax.
     */

    public Solucion asignarTareasGreedy(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;

        for (Tarea tarea : tareas) {
            solucionActual.incrementarCandidatos();
            boolean tareaAsignada = false;
            int index = 0;

            // Intenta asignar la tarea a un procesador que cumpla con el tiempo máximo permitido
            while (index < procesadores.size() && !tareaAsignada) {
                Procesador procesadorActual = solucionActual.getProcesadores().get(index);

                if (procesadorActual.puedeAsignarse(tarea, tiempoEjecucion) && procesadorActual.getTiempoProcesamiento() + tarea.getTiempoEjecucion() <= solucionActual.getTiempoEjecucion()) {
                    procesadorActual.add(tarea);
                    tareaAsignada = true;
                } else {
                    index++;
                }
            }

            // Si no se puede asignar a ningun procesador por el tiempo máximo, buscar el procesador con menor carga que pueda asignarse
            if (!tareaAsignada) {
                Procesador procesadorMenorCarga = null;
                for (Procesador p : procesadores) {
                    if ((procesadorMenorCarga == null || p.getTiempoProcesamiento() < procesadorMenorCarga.getTiempoProcesamiento())
                            && p.puedeAsignarse(tarea, tiempoEjecucion)) {
                        procesadorMenorCarga = p;
                    }
                }

                // Asignar al procesador de menor carga si se encuentra uno adecuado
                if (procesadorMenorCarga != null) {
                    procesadorMenorCarga.add(tarea);
                }
            }
        }

        respuesta = solucionActual.copy();
        return respuesta;
    }

    private List<Tarea> ordenarTareasPorTiempo(List<Tarea> critica, List<Tarea> noCritica) {
        List<Tarea> resultado = new ArrayList<>(critica);
        resultado.addAll(noCritica);

        resultado.sort(new TareaComparator().reversed());

        return resultado;
    }
}