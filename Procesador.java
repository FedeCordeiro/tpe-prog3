package tpe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Procesador {
    private String id;
    private String codigo;
    private boolean estaRefrigerado;
    private int anioFuncionamiento;
    private List<Tarea> tareas;

    public Procesador(String id, String codigo, boolean estaRefrigerado, int anioFuncionamiento) {
        this.id = id;
        this.codigo = codigo;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
        this.tareas = new ArrayList<Tarea>();
    }

    public String getId() { return id; }

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
    }

    public void delete(Tarea t) {
        tareas.remove(t);
    }

    public List<Tarea> getTareasCriticas() {
        List<Tarea> tareasCriticas = new ArrayList<>();
        if (!this.tareas.isEmpty()){
            for (Tarea t : this.tareas) {
                if (t.esCritica()){
                    tareasCriticas.add(t);
                }
            }
        }return tareasCriticas;
    }

    public Procesador copy(){
        Procesador p_copy = new Procesador(this.id, this.codigo,this.estaRefrigerado, this.anioFuncionamiento);
        for (Tarea t : tareas) {
            p_copy.add(t.copy());
        }
        return p_copy;
    }

    public int getTiempoProcesamiento() {
        int tiempo = 0;
        if (!tareas.isEmpty()){
            for (Tarea t : tareas){
                tiempo+= t.getTiempoEjecucion();
            }
        } return tiempo;
    }

    public boolean contieneTarea(Tarea t){
        return this.tareas.contains(t);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Procesador ID: ").append(id).append(", Código: ").append(codigo);
        sb.append(", Refrigerado: ").append(estaRefrigerado);
        sb.append(", Año: ").append(anioFuncionamiento);
        sb.append("\nTareas asignadas:\n");
        if (tareas.isEmpty()){
            sb.append("No hay tareas asignadas").append("\n");
        } else {
            for (Tarea tarea : tareas) {
                sb.append("  - ").append(tarea.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}