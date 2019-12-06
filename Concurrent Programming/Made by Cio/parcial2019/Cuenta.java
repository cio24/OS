package parcial2019;

public class Cuenta {
    private int monto;
    private int idCliente;
    private boolean enUso;

    public Cuenta(int monto, int idCliente){
        this.monto = monto;
        this.idCliente = idCliente;
        enUso = false;
    }

    public boolean isEnUso() {
        return enUso;
    }

    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }

    public int getIdCliente(){
        return this.idCliente;
    }

    public synchronized boolean extraer(int monto){
        if(this.monto < monto){
            return false;
        }
        this.monto -= monto;
        return true;
    }

    public synchronized void depositar(int monto){
        this.monto += monto;
    }
}
