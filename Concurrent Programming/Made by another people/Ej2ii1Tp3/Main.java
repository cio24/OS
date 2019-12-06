package Ej2ii1Tp3;

import Ej2ii1Tp3.Tarea;

public class Main {
	public static void main(String[] args) {
		//Thread t1= new Thread(new Tarea(500,500));
		//Thread t2= new Thread(new Tarea(1500,1500));
		//Thread t3= new Thread(new Tarea(500,500));
		Thread t4= new Thread(new Tarea(2000,2000));
		//t1.start();
		//t2.start();
		//t3.start();
		t4.start();
	}
}
