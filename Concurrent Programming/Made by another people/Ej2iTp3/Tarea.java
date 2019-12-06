package Ej2iTp3;

public class Tarea implements Runnable {
	private int n;
	public Tarea(int n) {
		super();
		this.n=n;
	}
	public void run() {
		int threadId = (int) Thread.currentThread().getId();
		for(int i=0; i<=this.n; i++) {
			System.out.println("Hola soy el Thread: " + threadId + " ejecutando por " + i + " vez");
		}
		
	}

}
