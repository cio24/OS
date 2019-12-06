package Barba;

import java.util.LinkedList;
import java.util.List;

public class Barberia {
	private int capacidad;
	private List<Cliente> pendientes=new LinkedList<>();
	public Barberia(int n) {
		this.capacidad=n;
	}
	public synchronized boolean hayLugar(Cliente c) {//llamado por cliente
		if(pendientes.size()<=capacidad) {
			pendientes.add(c);
			this.notify();//notifica al barbero
			return true;
		}
		return false;
	}
	public synchronized Cliente tomarCliente() {//llamado por barbero
		while(pendientes.isEmpty()) {
			try {
			this.wait();}
			catch(InterruptedException e) {
	            e.printStackTrace();
	        }//se duermo sobre el monitor
		}
		return pendientes.remove(0);
	}
}
