package BlancaNieves;

import java.util.LinkedList;
import java.util.List;

public class Casa {
	private int sillas=4;
	private List<Enanito> enanitosPendientes= new LinkedList<>();
	public Casa() {}
	public synchronized boolean haySillaLibre(Enanito e) { //llamado por enanito
		if(enanitosPendientes.size()<=this.sillas) {
			this.enanitosPendientes.add(e);
			this.notify();
			return true;
		}
		else return false;
	}
	public synchronized Enanito siguiente() { //llamado por blanca nieves
		while(enanitosPendientes.isEmpty()) {
			try {
			this.wait(); //se va a pasear
		}catch (InterruptedException e) {
            e.printStackTrace();
        }}
		return enanitosPendientes.remove(0);//se le da un enanito pendiente
	}
	
}
