package tpe;

import java.util.ArrayList;
import java.util.List;

public class Procesador {
    private String id;
    private String codigo;
    private boolean estaRefrigerado;
    private int anioFuncionamiento;

    private List<Tarea> tareas;
    private int tiempoTotalEjecucion; // Modificacion - Tiempo total de ejecución de las tareas asignadas
    private int cantidadTareasCriticas; // Modificacion - Cantidad de tareas críticas asignadas

    public Procesador(String id, String codigo, boolean estaRefrigerado, int anioFuncionamiento) {
        this.id = id;
        this.codigo = codigo;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
        this.tareas = new ArrayList<>();
        this.tiempoTotalEjecucion = 0;
        this.cantidadTareasCriticas = 0;
    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getAnioFuncionamiento() {
        return anioFuncionamiento;
    }

    public boolean esRefrigerado() {
        return estaRefrigerado;
    }

    public void add(Tarea t) {
        tareas.add(t);
        tiempoTotalEjecucion += t.getTiempoEjecucion(); // Actualiza el tiempo total
        if (t.esCritica()) {
            cantidadTareasCriticas++; // Incrementa el contador de tareas críticas
        }
    }

    public void delete(Tarea t) {
        if (tareas.remove(t)) { // Solo actualiza si la tarea se eliminó exitosamente
            tiempoTotalEjecucion -= t.getTiempoEjecucion(); // Actualiza el tiempo total
            if (t.esCritica()) {
                cantidadTareasCriticas--; // Decrementa el contador de tareas críticas
            }
        }
    }

    public int getTiempoProcesamiento() {
        return tiempoTotalEjecucion; // Modificación - Devuelve el tiempo acumulado
    }


    public int getCantidadTareasCriticas() {
        return cantidadTareasCriticas; // Modificación - Devuelve el contador acumulado
    }

    public boolean contieneTarea(Tarea t) {
        return this.tareas.contains(t);
    }

    public Procesador copy() {
        Procesador p_copy = new Procesador(this.id, this.codigo, this.estaRefrigerado, this.anioFuncionamiento);
        for (Tarea t : tareas) {
            p_copy.add(t.copy());
        }
        return p_copy;
    }

    public List<Tarea> getTareasCriticas() {
        List<Tarea> tareasCriticas = new ArrayList<>();
        for (Tarea t : this.tareas) {
            if (t.esCritica()) {
                tareasCriticas.add(t);
            }
        }
        return tareasCriticas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Procesador ID: ").append(id).append(", Código: ").append(codigo);
        sb.append(", Refrigerado: ").append(estaRefrigerado);
        sb.append(", Año: ").append(anioFuncionamiento);
        sb.append("\nTareas asignadas:\n");
        if (tareas.isEmpty()) {
            sb.append("No hay tareas asignadas").append("\n");
        } else {
            for (Tarea tarea : tareas) {
                sb.append("  - ").append(tarea.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public boolean puedeAsignarse(Tarea t, int tiempo) {
        if ((t.esCritica() && this.getCantidadTareasCriticas() < 2 || !t.esCritica())) {
            if ((!this.esRefrigerado() && this.getTiempoProcesamiento() + t.getTiempoEjecucion() <= tiempo || this.esRefrigerado())) {
                return true;
            }
        }
        return false;
    }
}