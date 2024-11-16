package tpe;

import java.util.ArrayList;
import java.util.List;

public class Greedy {
    private Solucion respuesta;
    private Solucion solucionActual;
    private List<Procesador> procesadores;
    private List<Tarea> tareas;
    private int tiempoEjecucion;

    public Greedy(List<Procesador> procesadores, List<Tarea> critica, List<Tarea> noCritica) {
        this.respuesta = new Solucion();
        this.procesadores = procesadores;
        this.solucionActual = new Solucion(this.procesadores, 0);
        this.tareas = ordenarTareasPorTiempo(critica, noCritica);
        this.tiempoEjecucion = 0;
    }

    /**
     * Estrategia Greedy
     * Ordena las tareas en función de su tiempo de ejecución, de forma descendente (de mayor a menor).
     * Recorre las tareas en orden y busca un procesador adecuado para asignarlas.
     * En el caso que una tarea no se pueda asignar por tiempoMax, se busca el procesador con menor carga.
     */
    public Solucion asignarTareasGreedy(int tiempoEjecucion) {
        if (procesadores.isEmpty() || tareas.isEmpty()) {
            return null;
        } else {
            this.tiempoEjecucion = tiempoEjecucion;

            for (Tarea tarea : tareas) {
                boolean tareaAsignada = false;
                int index = 0;

                // Intenta asignar la tarea a un procesador que cumpla con el tiempo máximo permitido
                while (index < procesadores.size() && !tareaAsignada) {
                    Procesador procesadorActual = solucionActual.getProcesadores().get(index);

                    if (procesadorActual.puedeAsignarse(tarea, tiempoEjecucion)
                            && procesadorActual.getTiempoProcesamiento() + tarea.getTiempoEjecucion() <= solucionActual.getTiempoEjecucion()) {
                        procesadorActual.add(tarea);
                        tareaAsignada = true;
                        solucionActual.incrementarCandidatos();
                    } else {
                        index++;
                    }
                }

                // Si no se puede asignar a ningún procesador, buscar el procesador con menor carga
                if (!tareaAsignada && !asignarAlProcesadorDeMenorCarga(tarea)) {
                    return null; // Si no se encuentra un procesador válido, retornar null
                }
            }
        }

        // Copiar la solución actual como respuesta final
        respuesta = solucionActual.copy();
        return respuesta;
    }

    //Modificacion - Modularizacion del metodo
    //Busca el procesador con menor carga que pueda asignarse la tarea y la asigna.
    private boolean asignarAlProcesadorDeMenorCarga(Tarea tarea) {
        Procesador procesadorMenorCarga = null;

        for (Procesador p : procesadores) {
            if ((procesadorMenorCarga == null || p.getTiempoProcesamiento() < procesadorMenorCarga.getTiempoProcesamiento())
                    && p.puedeAsignarse(tarea, tiempoEjecucion)) {
                procesadorMenorCarga = p;
            }
        }

        if (procesadorMenorCarga != null) {
            procesadorMenorCarga.add(tarea);
            solucionActual.incrementarCandidatos();
            return true;
        }

        return false; // No se encontró un procesador válido
    }

    //Ordena las tareas por tiempo de ejecución en orden descendente.
    private List<Tarea> ordenarTareasPorTiempo(List<Tarea> critica, List<Tarea> noCritica) {
        List<Tarea> resultado = new ArrayList<>(critica);
        resultado.addAll(noCritica);

        resultado.sort(new TareaComparator().reversed());

        return resultado;
    }
}