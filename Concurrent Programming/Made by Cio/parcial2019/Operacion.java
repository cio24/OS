package parcial2019;

public class Operacion {
    private int codOp, idOrigen,idDestino, monto;

    public Operacion(int codOp, int idOrigen, int idDestino, int monto) {
        this.codOp = codOp;
        this.idOrigen = idOrigen;
        this.idDestino = idDestino;
        this.monto = monto;
    }

    public int getCodOp() {
        return codOp;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public int getIdDestino() {
        return idDestino;
    }


    public int getMonto() {
        return monto;
    }

}
