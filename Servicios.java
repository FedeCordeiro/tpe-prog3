package tpe;

import tpe.utils.CSVReader;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */

public class Servicios {

	private Map<String, Tarea> tareasMap = new HashMap<String, Tarea>();
	private List<Tarea> criticasList = new LinkedList <>();
	private List <Tarea> noCriticasList = new LinkedList <>();
	/*
     * Expresar la complejidad temporal del constructor.
     */

	// Constructor: Procesa los archivos y carga los datos
	// Complejidad temporal: O(n + m) donde n es la cantidad de procesadores y m la de tareas.

	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		reader.readTasks(pathTareas, tareasMap, criticasList, noCriticasList);
	}

	/*
     * Expresar la complejidad temporal del servicio 1.
     * Complejidad O(1)
     */

	public Tarea servicio1(String ID) {
		return this.tareasMap.get(ID);
	}

    /*
     * Expresar la complejidad temporal del servicio 2.
     * Si no me equivoco es O(1) A CHECKEAR
     */

	public List<Tarea> servicio2(boolean esCritica) {
	if (esCritica){
		return criticasList;
	} else return noCriticasList;
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     * O(n) siendo n la cantidad de tareas en el hashMap
     */

	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		List<Tarea> tareasEnRango = new LinkedList<>();

		for (Tarea tarea : tareasMap.values()) {
			if (tarea.getNivelPrioridad() >= prioridadInferior && tarea.getNivelPrioridad() <= prioridadSuperior) {
				tareasEnRango.add(tarea);
			}
		}
		return tareasEnRango;
	}

}
