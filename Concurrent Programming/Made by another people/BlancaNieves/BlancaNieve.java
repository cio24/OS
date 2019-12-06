package BlancaNieves;

public class BlancaNieve implements Runnable{
	private Casa c;
	public BlancaNieve(Casa c) {
		this.c=c;
	}
	public void run() {
		while(true) {
	        System.out.println("Thread "+ Thread.currentThread().getName() + " Tomando Enanito");
			Enanito actual=this.c.siguiente();
			try {
				Thread.sleep(1000);
				
			}catch(InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Thread "+ Thread.currentThread().getName() + " Le sirvo al enano");

			actual.setServido();//avisa al enanito que puede comer
	}
}}
