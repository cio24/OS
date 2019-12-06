package resolucion_parcial_2019;

public class Cliente implements Runnable {
    Banco b;
    Operacion op;
    Cajero cajero=null;
    String resultado=null;

    public Cliente (Banco b,Operacion o){
        this.b=b;
        this.op=o;
    }

    public void run (){
        b.tomarAsiento(this);
        synchronized (this){
            while (cajero==null){ //mientras no tenga un cajero asignado
                try {
                    this.wait(); //espera a que alguno se desocupe
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        cajero.operar (this.op); //aca se despierta y le asigna la operacion a realizar
        synchronized(this){
            while(resultado==null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("listo");

    }

    public synchronized void setCajero(Cajero cajero){ //aca le asigno un cajero al cliente si todavia no tiene uno y notifico para que se despierte y haga la operacion
        this.cajero=cajero;
        this.notify();
    }

    public void setResultado(String resultado){
        this.resultado=resultado;
    }
}

