package tpe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Greedy {
    private Solucion mejorSolucion;
    private Solucion solucionActual;
    private List<Procesador> procesadores;
    private List<Tarea> tareas;
    private int tiempoMaximoNoRefrigerado;

    public Greedy(List<Procesador> procesadores, List<Tarea> tareas) {
        this.mejorSolucion = new Solucion();
        this.solucionActual = new Solucion();
        this.procesadores = procesadores;
        this.tareas = tareas;
        this.tiempoMaximoNoRefrigerado = 0;
    }

    public Solucion asignarTareasGreedy(int tiempoMaximoNoRefrigerado) {
        this.tiempoMaximoNoRefrigerado = tiempoMaximoNoRefrigerado;

        ordenarTareasCriticasYTiempo(tareas);

        // Recorremos cada tarea para asignarla a un procesador válido.
        Iterator<Tarea> tareaIterator = tareas.iterator();
        while (tareaIterator.hasNext()) {
            Tarea tarea = tareaIterator.next();

            // Buscamos un procesador adecuado para esta tarea
            Procesador procesadorAsignado = null;
            for (Procesador procesador : procesadores) {
                if (puedeAsignarse(procesador, tarea)) {
                    procesadorAsignado = procesador;
                    break;
                }
            }

            if (procesadorAsignado != null) {
                procesadorAsignado.add(tarea);
                solucionActual.addProcesador(procesadorAsignado);
                tareaIterator.remove();
            }

            if (solucionActual.getTiempoEjecucion() < mejorSolucion.getTiempoEjecucion()) {
                mejorSolucion = solucionActual.copy();
            }
        }

        return mejorSolucion;
    }

    private void ordenarTareasCriticasYTiempo(List<Tarea> tareas) {
    }

    private void ordenarPorRefrigeracion(List<Procesador> procesadores) {
        int pos = 0;
        while (pos < procesadores.size() - 1) {
            if (!procesadores.get(pos).esRefrigerado() && procesadores.get(pos + 1).esRefrigerado()) {
                Procesador aux = procesadores.get(pos);
                procesadores.set(pos, procesadores.get(pos + 1));
                procesadores.set(pos + 1, aux);
                // Retrocedemos una posición si es posible para verificar nuevamente
                if (pos > 0) {
                    pos--;
                }
            }
            else {
                pos++;
            }
        }
    }

    private boolean puedeAsignarse(Procesador procesador, Tarea tarea) {

        return false;
    }
}