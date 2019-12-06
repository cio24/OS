package resolucion_parcial_2019;

public class Cuenta {
    double saldo_cuenta;
    int id_cuenta;

    public int getId_cuenta() {
        return this.id_cuenta;
    }

    public synchronized void depositar(double valor) {
        this.saldo_cuenta += valor;
    }

    public synchronized String extraer(double monto, Cajero cajero) {
        if (monto > saldo_cuenta) {
            return "saldo insuficiente";
        }
        if (cajero.sacarCaja(monto)) {
            saldo_cuenta -= monto;
            return "pago el cajero";
        }
        if (cajero.getBanco().atesorar(-monto)) {
            saldo_cuenta -= monto;
            return "pago tesoro";
        }
        return "corrida bancaria, volvio el corralitoh";
    }

    public String transferir(Cuenta destino, double monto) {
        int id_orig = this.id_cuenta;
        int id_dest = destino.getId_cuenta();
        Cuenta a = this;
        Cuenta b = destino;
        if (id_orig > id_dest) {
            a = destino;
            b = this;
        }
        synchronized (a) {
            synchronized (b) {
                if (monto > this.saldo_cuenta) {
                    return ("saldo insuficiente");
                }
                this.saldo_cuenta -= monto;
                destino.saldo_cuenta += monto;
            }
        }
        return "transferencia realizada";
    }
}