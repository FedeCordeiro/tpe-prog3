package tpe;

import java.util.ArrayList;
import java.util.List;

public class Solucion {

    private List<Procesador> procesadores;
    private int tiempo;
    private int estadosGenerados;

    public Solucion(){
        procesadores = new ArrayList<>();
        tiempo = 0;
        estadosGenerados = 0;
    }

    public Solucion(List<Procesador> procesadores , int tiempo) {
        this.procesadores = procesadores;
        this.tiempo = tiempo;
    }

    public Solucion copy(){ // si agregamos atributos tenerlo en cuenta
        Solucion solucion = new Solucion();
        solucion.setTiempo(this.tiempo);
        for (Procesador p : procesadores){
            solucion.addProcesador(p.copy());
        }
        return solucion;
    }

    public List<Procesador> getProcesadores() {
        return procesadores;
    }

    public int getTiempoEjecucion() { //retornar el tiempo de ejecucion mayor entre los procesadores
        int tiempoEjecucion = 0;
        for (Procesador p : procesadores) {
            if (p.getTiempoProcesamiento() > tiempoEjecucion){
                tiempoEjecucion = p.getTiempoProcesamiento();
                this.tiempo = tiempoEjecucion;
            }
        } return tiempoEjecucion;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void addProcesador(Procesador p){
        procesadores.add(p);
    }

    public boolean contieneTarea(Tarea t){
        boolean contiene = false;
        for (Procesador p : procesadores){
            if (p.contieneTarea(t)){
                contiene = true;
            }
        } return contiene;
    }

    public void incrementarGenerados(){
        estadosGenerados++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Procesador procesador : procesadores) {
            sb.append(procesador.toString()).append("\n");
        }
        sb.append("TIEMPO TOTAL: ").append(tiempo).append("\n");
        sb.append("ESTADOS GENERADOS: ").append(estadosGenerados);
        return sb.toString();
    }
}
