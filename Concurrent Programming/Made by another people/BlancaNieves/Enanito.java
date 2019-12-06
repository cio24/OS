package BlancaNieves;

public class Enanito implements Runnable {
	private boolean servido=false;
	private Casa c;
	
	public Enanito(Casa c) {
		this.c=c;

	}
	public void run() {
		boolean hayLugar;
		while(true) {
			hayLugar=this.c.haySillaLibre(this);
			if(hayLugar) {
		        System.out.println("Thread "+ Thread.currentThread().getName() + " Estoy esperando");

				synchronized(this) {
				while(!servido) {
				try {
					this.wait();}
					catch (InterruptedException e) {
			            e.printStackTrace();
			        }
				}}
				try {
			        System.out.println("Thread "+ Thread.currentThread().getName() + " Como");

				Thread.sleep(1000);}//come
				catch (InterruptedException e) {
		            e.printStackTrace();
		        }
			}
			else 
		        System.out.println("Thread "+ Thread.currentThread().getName() + " Trabajo");

				try {
				Thread.sleep(1000);}//vuelve a trabajar
				catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		
	}
		}
	public synchronized  void setServido() {//Llamado por blanca nieves
		this.servido=true;
		this.notify();
		
	}
}
