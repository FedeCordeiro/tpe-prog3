package tpe;

import tpe.utils.CSVReader;

import java.util.HashMap;
import java.util.Map;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */

public class Servicios {

	private Map<String, Tarea> tareasMap = new HashMap<String, Tarea>();
	private Map<String, Procesador> procesadoresMap = new HashMap<String, Procesador>();
	/*
     * Expresar la complejidad temporal del constructor.
     */

	// Constructor: Procesa los archivos y carga los datos
	// Complejidad temporal: O(n + m) donde n es la cantidad de procesadores y m la de tareas.

	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		this.procesadoresMap = reader.readProcessors(pathProcesadores);
		this.tareasMap = reader.readTasks(pathTareas);
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
     */

	/* public List<Tarea> servicio2(boolean esCritica) {
	return null;
	} */

    /*
     * Expresar la complejidad temporal del servicio 3.
     */

	/* public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {

	} */

}
