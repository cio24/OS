package parcial2019;

import java.util.ArrayList;

public class Banco {
    private int tesoro;
    private ArrayList<Cliente> clientes;
    private ArrayList<Cuenta> cuentas;

    public Banco(ArrayList<Cuenta> cuentas) {
        this.clientes = new ArrayList<>();
        this.cuentas = cuentas;
    }

    public void addCliente(Cliente c){
        clientes.add(c);
        notifyAll(); //se llama a todos los cajeros para que alguno lo atienda
    }

    public synchronized Cliente getCliente(){
        while(clientes.isEmpty()){
            try {
                    wait(); //el cajero se duerme en el banco xD hasta que allá clientes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return clientes.remove(0);
    }

    public synchronized boolean extraerTesoro(int monto){
        if(tesoro < monto){
            return false;
        }
        tesoro -= monto;
        return true;
    }

    public synchronized void depositarTesoro(int monto){
        tesoro += monto;
    }

    public Cuenta getCuenta(int idCliente){ //no lo hago sincronizado xq supongo que no se va a modificar esto!
        for(Cuenta c: cuentas){
            if(c.getIdCliente() == idCliente){
                return c;
            }
        }
        return null;
    }
}