package Ej2Tp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		int id=0;
		List<String> datos =
			Collections.synchronizedList(new ArrayList<String>());
		Recurso r1= new Recurso(id, Collections.synchronizedList(new ArrayList<String>()));
		Recurso r2= new Recurso(id, datos);
		Recurso r3= new Recurso(id, datos);
		Thread t1= new Thread(new Tarea(r1, r2, 1, 2), "T1");
		Thread t2= new Thread(new Tarea(r2, r3, 3, 4), "T2");
		t1.start();
		t2.start();
		try { //entra si no hay excepcion, pero no es una excepcion
			t1.join(); //espera hasta que el thread1 termine
			t2.join(); //thread main debe esperar a que sus thread hijos terminen
		} catch (InterruptedException e){
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
