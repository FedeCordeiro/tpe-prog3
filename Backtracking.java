package tpe;

import java.util.ArrayList;
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
        this.tareas = this.tareasCriticas.addAll(tareasNoCriticas);
        this.tiempoEjecucion = 0;
    }

    public Solucion asignarTareasBack(int tiempoEjecucion){
        if(procesadores.size()*2 < this.tareasCriticas.size() || procesadores.size()==0 || tareas.size()==0){
            return null;
        }else{
            this.tiempoEjecucion = tiempoEjecucion;
            Solucion solParcial = new Solucion(this.procesadores);
            solucion=null; //reiniciamos la variable
            int index=0;
            return asignarTareasBack(solParcial, index);
        }
    }

    private Solucion asignarTareasBack(Solucion solParcial, int index){
        if(tareas.size()==index){
            if(this.solucion == null || solParcial.getTiempoEjecucion()<solucion.getTiempoEjecucion()){
                solucion = solParcial.copy();// borrar la solucion anterior y copiar la nueva
                return solucion;
            }
        }else{
            Iterator<Procesador> it = solParcial.getProcesadores().iterator();
            Tarea t = tareas[index];
            while(it.hasNext()){
                Procesador p = it.next();
                if(t.esCritica() && p.getTareasCriticas().size()<2 && (!p.esRefrigerado() || (p.esRefrigerado() && p.getTiempoEjecucion()+t.getTiempoEjecucion()<=this.tiempoEjecucion))){
                    if(p.getTiempoEjecucion()+t.getTiempoEjecucion()< this.solucion.getTiempoEjecucion()){
                        p.add(t);
                        return asignarTareasBack(solParcial, index+1);
                        p.delete(t);
                    }
                }
            } if(!solParcial.contieneTarea(t[index])){
                return null;
            }
        }
        return null;
    }


}
