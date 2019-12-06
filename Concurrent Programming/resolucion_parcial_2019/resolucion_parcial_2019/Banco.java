package resolucion_parcial_2019;

import java.util.ArrayList;

public class Banco {
        double tesoro=1000000; //el tesoro es un millón
        ArrayList<Cliente> colaEspera= new ArrayList<> ();

        public synchronized boolean atesorar(double monto){ //sincronizo porque dos cajas pueden querer ir al tesoro y hacer cagada
        if (this.tesoro + monto < 0){
            return false;
        } else {
            tesoro +=monto;
            return true;
        }
    }

        public void tomarAsiento(Cliente cliente){
            synchronized (this.colaEspera){
                this.colaEspera.add(cliente);
                this.colaEspera.notify();
            }
    }

        public Cliente proxCliente(){
            synchronized(this.colaEspera){
                while (this.colaEspera.size()==0){
                    try {
                        this.colaEspera.wait();
                    } catch (InterruptedException e) {

                    }
                }
            }
            return this.colaEspera.remove(0);
    }
}
