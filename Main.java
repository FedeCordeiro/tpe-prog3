package tpe;

import java.util.ArrayList;

public class Main {
	public static void main(String args[]) {
		Servicios servicios = new Servicios("./tpe/datasets/Procesadores.csv", "./tpe/datasets/Tareas.csv");

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
	}

	public static class Solucion {

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

	}
}