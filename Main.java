package tpe;

import tpe.utils.CSVReader;

import java.util.*;

public class Main {
	public static void main(String args[]) {
		Servicios servicios = new Servicios("./datasets/Procesadores.csv", "./datasets/Tareas.csv");

		//Dado un identificador de tarea obtener toda la información de la tarea asociada.
		System.out.println("Informacion de tarea:");
		System.out.println(servicios.servicio1("T1"));
		System.out.println();

		//Ver todas las tareas críticas
		System.out.println("Lista de tareas criticas:");
		System.out.println(servicios.servicio2(true));
		System.out.println();

		//Ver todas las tareas NO críticas
		System.out.println("Lista de tareas NO criticas:");
		System.out.println(servicios.servicio2(false));
		System.out.println();

		//Obtener todas las tareas entre dos niveles de prioridad
		System.out.println("Lista de tareas por rango:");
		System.out.println(servicios.servicio3(10,50));
		System.out.println();

		CSVReader lector = new CSVReader();
		List<Procesador> procesadores = new ArrayList<>();
		lector.readProcessors("./datasets/Procesadores.csv", procesadores);
		List<Tarea> noCritica = servicios.servicio2(false);
		List<Tarea> critica = servicios.servicio2(true);
		Backtracking test = new Backtracking(procesadores, critica, noCritica);

		Solucion solucionado = test.asignarTareasBack(80);

		System.out.println();
		System.out.println("---BACKTRACKING---");
		// Modificación - Si la solución es null, se imprime mensaje de aviso
		if (solucionado == null) {
			System.out.println("No hay una solución valida para la lista de tareas, utilizando Backtracking");
		}
		else {
			System.out.println(solucionado);
		}


		Greedy g = new Greedy(procesadores, critica, noCritica);
		Solucion solucionado1 = g.asignarTareasGreedy(80);
		System.out.println();
		System.out.println("---GREEDY---");
		// Modificación - Si la solución es null, se imprime mensaje de aviso
		if (solucionado1 == null) {
			System.out.println("No hay una solución valida para la lista de tareas, utilizando Greedy");
		}
		else {
			System.out.println(solucionado1);
		}

	}

}