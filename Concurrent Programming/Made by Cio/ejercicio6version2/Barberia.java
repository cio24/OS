package ejercicio6version2;

import java.util.ArrayList;

public class Barberia {
    private ArrayList<Cliente> sillas;
    public static final int MAX_SILLAS = 5;

    public Barberia(){
        sillas = new ArrayList<>();
    }

    public synchronized boolean entrar(Cliente c){
        if(sillas.size() < MAX_SILLAS){
            sillas.add(c);
            notifyAll();
            return true;
        }
        return false;
    }

    public synchronized Cliente llamarCliente(){
            if(sillas.size() > 0){
                return sillas.remove(0);
            }
            return null;
    }
}
