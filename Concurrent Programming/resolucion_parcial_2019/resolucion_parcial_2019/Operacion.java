package resolucion_parcial_2019;

public class Operacion {
    int codigo_op;
    Cuenta origen;
    Cuenta destino;
    Cajero cajero;
    double monto;

    public int getCodigo(){
        return this.codigo_op;
    }

    public Cuenta getCuenta(){
        return this.origen;
    }

    public Cuenta getCuentaDest(){
        return this.destino;
    }

    public double getMonto(){
        return this.monto;
    }

    public void setMonto(double monto){
        this.monto=monto;
    }

    public Cajero getCajero(){
        return this.cajero;
    }

}
