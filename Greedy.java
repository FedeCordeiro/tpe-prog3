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
     * Estrategia Greedy
     * 1.Ordena las tareas en función de su tiempo de ejecución, de forma descendente (de mayor a menor).
     * 2.Recorre las tareas en orden y se manejan los procesadores a traves de un indice.
     * 3.En el caso que una tarea no se pueda asignar porque superaria el tiempoMax, se busca el procesador con menor carga y valido para asignar la tarea.
     */
    public Solucion asignarTareasGreedy(int tiempoEjecucion) {
        // Modificación - Se agrega control y se retorna null
        if (procesadores.isEmpty() || tareas.isEmpty()) {
            return null;
        }
        else {
            this.tiempoEjecucion = tiempoEjecucion;

            for (Tarea tarea : tareas) {
                boolean tareaAsignada = false;
                int index = 0;

                // Intenta asignar la tarea a un procesador que cumpla con el tiempo máximo permitido
                while (index < procesadores.size() && !tareaAsignada) {
                    Procesador procesadorActual = solucionActual.getProcesadores().get(index);

                    if (procesadorActual.puedeAsignarse(tarea, tiempoEjecucion) && procesadorActual.getTiempoProcesamiento() + tarea.getTiempoEjecucion() <= solucionActual.getTiempoEjecucion()) {
                        procesadorActual.add(tarea);
                        solucionActual.incrementarCandidatos();
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
                        solucionActual.incrementarCandidatos();
                    }
                    else {
                        // Modificación - Si no se puede asignar a ningun procesador, retorna null
                        return null;
                    }
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