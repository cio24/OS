package Ej2iTp3;


public class Main {
	public static void main(String[] args) {
		Thread t1= new Thread(new Tarea(4));
		Thread t2= new Thread(new Tarea(4));
		Thread t3= new Thread(new Tarea(4));
		Thread t4= new Thread(new Tarea(4));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
