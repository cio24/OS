package parcial2019;

public class Cliente implements Runnable {
    private Banco banco;
    private String nombre;
    private Operacion operacion;
    public Cliente(Banco banco,Operacion operacion, String nombre){
        this.operacion = operacion;
        this.banco = banco;
        this.nombre = nombre;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void run(){
        banco.addCliente(this);
    }
}
