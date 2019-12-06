package mates;

public class Facturas {
    private int cantidad;
    public Facturas(int cantidad){
        this.cantidad = cantidad;
    }

    public synchronized boolean agarrarFacturas(){
        if(cantidad > 0){
            cantidad--;
            return true;
        }
        return false;
    }
}
