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

    public Solucion asignarTareasBack(int tiempoEjecucion){
        if(procesadores.size()*2 < this.tareasCriticas.size() || procesadores.isEmpty() || tareas.isEmpty()){
            return null;
        }else{
            this.tiempoEjecucion = tiempoEjecucion;
            Solucion solParcial = new Solucion(this.procesadores,0);
            this.solucion= new Solucion(); //reiniciamos la variable
            int index=0;
            asignarTareasBack(solParcial, index);
            return this.solucion;
        }
    }

    private void asignarTareasBack(Solucion solParcial, int index){
        solucion.incrementarGenerados();
        if(tareas.size()==index){
            if(this.solucion.getTiempoEjecucion() == 0  || solParcial.getTiempoEjecucion()<solucion.getTiempoEjecucion()){
                solucion = solParcial.copy();// borrar la solucion anterior y copiar la nueva
            }
        }else{
            Iterator<Procesador> it = solParcial.getProcesadores().iterator();
            Tarea t = tareas.get(index);
            while(it.hasNext()){
                Procesador p = it.next();
                if((t.esCritica() && p.getTareasCriticas().size()<2 || !t.esCritica())){
                    if ((!p.esRefrigerado() && p.getTiempoProcesamiento()+t.getTiempoEjecucion()<=this.tiempoEjecucion || p.esRefrigerado())) {
                        if (p.getTiempoProcesamiento() + t.getTiempoEjecucion() < this.solucion.getTiempoEjecucion() || solucion.getTiempoEjecucion() == 0) {
                            p.add(t);
                            asignarTareasBack(solParcial, index + 1);
                            p.delete(t);
                        }
                    }
                }
            }
        }
    }


}
