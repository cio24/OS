package Barba;

public class Cliente implements Runnable{
	private Barberia b;
	private boolean peloCortado=false;
	
	public Cliente(Barberia b) {
		this.b=b;
	}
	public void run() {
		boolean haySilla=b.hayLugar(this);
		if(haySilla) {
			synchronized(this) {
				while(!peloCortado) {
					try {
					this.wait();}//se duermo sobre su monitor
					catch(InterruptedException e) {
			            e.printStackTrace();
			        }
				}
//se va
			}
		}
		
	}
	public synchronized void setPeloCortado() {
		this.peloCortado=true;
		this.notify();
	}
}
