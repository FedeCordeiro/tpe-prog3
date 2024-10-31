package tpe;

import java.util.Collection;

public class Procesador {
    private String id;
    private String codigo;
    private boolean estaRefrigerado;
    private int anioFuncionamiento;

    public Procesador(String id, String codigo, boolean estaRefrigerado, int anioFuncionamiento) {
        this.id = id;
        this.codigo = codigo;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
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
    }

    public void delete(Tarea t) {
    }

    public Collection<Object> getTareasCriticas() {
    }

    public int getTiempoEjecucion() {
    }
}