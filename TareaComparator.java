package tpe;

import java.util.Comparator;

public class TareaComparator implements Comparator<Tarea> {
    public int compare(Tarea t1, Tarea t2) {
       return Integer.compare(t1.getTiempoEjecucion(), t2.getTiempoEjecucion());
    }
}
