package tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
        this.tareas = ordenarTareasCriticasYTiempo(Critica, Nocritica);
        this.tiempoEjecucion = 0;
    }

    public Solucion asignarTareasGreedy(int tiempoEjecucion) {
        int index = 0;
        this.tiempoEjecucion = tiempoEjecucion;

        // Recorremos cada tarea para asignarla a un procesador válido.
        for (Tarea tarea : tareas) {
            solucionActual.incrementarCandidatos();
            if (index == procesadores.size()) {
                index = 0;
            }
            while (index < procesadores.size()) {
                Procesador procesadorAsignado = solucionActual.getProcesadores().get(index);
                if (procesadorAsignado.puedeAsignarse(tarea, tiempoEjecucion)) {
                    procesadorAsignado.add(tarea);
                    index++;
                    break;
                }
                else {
                    index++;
                }
            }
        }
        respuesta = solucionActual.copy();
        return respuesta;
    }

    private List<Tarea> ordenarTareasCriticasYTiempo(List<Tarea> critica, List<Tarea> noCritica) {
        List<Tarea> resultado = new ArrayList<>(critica);

        resultado.sort(new TareaComparator().reversed());
        noCritica.sort(new TareaComparator().reversed());

        resultado.addAll(noCritica);
        return resultado;
    }

}