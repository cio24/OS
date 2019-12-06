package resolucion_parcial_2019;

public class Cajero {
    static int codigo_deposito=1;
    static int codigo_transferir=2;
    static double MAX=100000;
    static double MIN=500000;

    Banco b;
    Operacion op;
    Cliente cliente=null;
    double saldo;

    public Cajero (Banco b){
        this.b=b;
    }

    public Banco getBanco(){
        return this.b;
    }

    public synchronized void operar(Operacion op){ //le asigno la operacion arealziar al cajero
        this.op=op;
        this.notify();
    }


    public boolean sacarCaja (double monto){
        if(monto < this.saldo){
            this.saldo-=monto;
            return true;
        }
        else
            return false;
    }

    public void run (){
        while (true){
            this.cliente=b.proxCliente(); //llamas al proximo cliente. Osea, el cliente actual deja de ser null y se asigna
            this.cliente.setCajero (this); //al cliente le setteo su cajero
            synchronized(this){
                while (op==null){ //mientras el cajero no tenga una operacion asignada para ejecutar, espera
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (op.getCodigo()==codigo_deposito){
                op.getCuentaDest().depositar(op.getMonto()); //en el codigo de cuchi era op.getCajero().depositar(op.getMonto()) pero no tiene sentido porque el cajero no puede depositar
                cliente.setResultado("ok");
            } else{
                if (op.getCodigo()==codigo_transferir){
                    Cuenta c_origen=op.getCuenta();
                    Cuenta c_destino=op.getCuentaDest();
                    String respuesta =c_origen.transferir(c_destino,op.getMonto());
                    cliente.setResultado(respuesta);
                }else {
                    String respuesta=op.getCuenta().extraer(op.getMonto(),this);
                    cliente.setResultado(respuesta);
                }

            }
            if ((saldo<MIN) || (saldo>MAX)){
                double diferencia= ((MIN + MAX) / 2) - saldo;
                if (b.atesorar(-diferencia))
                    saldo+=diferencia;
            }
            op=null;
        }
    }
}
