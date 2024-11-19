package tpe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Backtracking {

    private Solucion solucion;
    private List<Procesador> procesadores;
    private List<Tarea> tareasCriticas;
    private List<Tarea> tareas;
    private int tiempoEjecucion;

    public Backtracking(List<Procesador> procesadores, List<Tarea> tareasCriticas, List<Tarea> tareasNoCriticas) {
        this.solucion = null;
        this.procesadores = procesadores;
        this.tareasCriticas = tareasCriticas;
        this.tareas = new ArrayList<>(tareasCriticas);
        this.tareas.addAll(tareasNoCriticas);
        this.tiempoEjecucion = 0;
    }

    /**
     * El algoritmo funciona de la siguiente manera:
     * 1. Comienza asignando la primera tarea disponible a un procesador que pueda ejecutarla sin exceder el tiempo límite.
     * 2. Luego recurre de manera recursiva para asignar la siguiente tarea a otro procesador.
     * 3. Si en algún momento una tarea no puede ser asignada o una asignación no lleva a una mejor solución, el algoritmo
     * retrocede (backtrack) y prueba con otras combinaciones de asignaciones.
     * 4. Al finalizar todas las asignaciones, se evalúa si la solución encontrada es mejor (es decir, tiene un menor tiempo
     * total de ejecución) que la solución previamente almacenada. Si es así, se actualiza la mejor solución encontrada.
     */

    public Solucion asignarTareasBack(int tiempoEjecucion) {
        if (procesadores.size() * 2 < this.tareasCriticas.size() || procesadores.isEmpty() || tareas.isEmpty()) {
            return null;
        } else {
            this.tiempoEjecucion = tiempoEjecucion;
            Solucion solParcial = new Solucion(this.procesadores, 0);
            this.solucion = new Solucion(); // Reiniciar solucion
            int index = 0;
            asignarTareasBack(solParcial, index);
            return this.solucion;
        }
    }

    private void asignarTareasBack(Solucion solParcial, int index) {
        solucion.incrementarGenerados();
        if (tareas.size() == index) {
            if (this.solucion.getTiempoEjecucion() == 0 || solParcial.getTiempoEjecucion() < solucion.getTiempoEjecucion()) {
                solucion = solParcial.copy();
            }
        } else {
            Tarea t = tareas.get(index);
            boolean sePudoAsignar = false;

            for (Procesador p : solParcial.getProcesadores()) {
                if (p.puedeAsignarse(t, tiempoEjecucion)) {
                    if (p.getTiempoProcesamiento() + t.getTiempoEjecucion() < this.solucion.getTiempoEjecucion() || solucion.getTiempoEjecucion() == 0) {
                        p.add(t);
                        sePudoAsignar = true;
                        asignarTareasBack(solParcial, index + 1);
                        p.delete(t);
                    }
                }
            }

            // Modificacion - Si no se pudo asignar la tarea, hacemos un return y queda en null
            if (!sePudoAsignar) {
                return;
            }
        }
    }
}
