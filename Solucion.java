package tpe;

import java.util.ArrayList;

public class Solucion {

    private ArrayList<Procesador> procesadores;
    private int tiempo;

    public Solucion(int tiempo) {
        this.procesadores = new ArrayList<Procesador>();
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

    public Iterable<Object> getProcesadores() {
    }

    public int getTiempoEjecucion() {
    }
}
