package Barba;

public class Barbero implements Runnable{
	private Barberia b;
	public Barbero(Barberia b) {
		this.b=b;
	}
	public void run() {
		while(true) {//siempre trabaja
			Cliente actual=b.tomarCliente();
			try {
			Thread.sleep(1000);} //simula que corta pelo
			catch(InterruptedException e) {
	            e.printStackTrace();
	        }
			actual.setPeloCortado();
		}
	}
}
