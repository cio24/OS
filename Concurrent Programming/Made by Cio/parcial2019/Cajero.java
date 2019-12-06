package parcial2019;

public class Cajero implements Runnable {
    private Banco banco;
    private int saldoCaja, min, max;

    public Cajero(Banco banco, int saldoCaja, int min, int max) {
        this.banco = banco;
        this.saldoCaja = saldoCaja;
        this.min = min;
        this.max = max;
    }

    private void actualizarCaja(){
        if(saldoCaja > max){ //si mi caja se pasa lo agrego al tesoro
            banco.depositarTesoro(saldoCaja - (max - min)/2);
            saldoCaja = (max - min)/2;
        }
        else{ //si a mi caja le falta saco del tesoro
            banco.extraerTesoro((max - min)/2 - saldoCaja);
            saldoCaja = (max - min)/2;
        }
    }

    @Override
    public void run() {
        while(true){
            Cliente cliente = banco.getCliente();
            Operacion operacion = cliente.getOperacion();
            Cuenta cuentaOrigen,cuentaDestino;
            int montoOperacion = operacion.getMonto();

            switch (operacion.getCodOp()) {
                case 1: //deposito
                    cuentaOrigen = banco.getCuenta(operacion.getIdOrigen()); //obtengo la cuenta de origen
                    cuentaOrigen.depositar(montoOperacion);
                    saldoCaja += montoOperacion; //agrego a mi caja lo que se deposito
                    actualizarCaja();
                    break;
                case 2: //extraccion
                    cuentaOrigen = banco.getCuenta(operacion.getIdOrigen()); //obtengo la cuenta
                    synchronized (cuentaOrigen){//la sincronizo para evitar que nadie haga nada hasta que termine
                        if(montoOperacion > saldoCaja){ //si me pide más de lo que tengo en la caja
                            if(!banco.extraerTesoro(montoOperacion - saldoCaja)){ //voy por lo que me falta al tesoro
                                System.out.println("No hay suficientemente plata en el banco");
                                break; //se termino!

                            }
                            else{
                                saldoCaja = montoOperacion; //ahora tengo lo que me piden
                            }
                        }
                        if(!cuentaOrigen.extraer(montoOperacion)){ //extraigo de la cuenta del cliente
                            System.out.println("La cuenta no tiene saldo suficiente");
                        }
                    }
                    saldoCaja -= operacion.getMonto(); //actualizo el saldo de mi caja
                    actualizarCaja();
                    break;
                case 3: //transferencia
                    cuentaOrigen = banco.getCuenta(operacion.getIdOrigen());
                    if(!cuentaOrigen.extraer(montoOperacion)){
                        System.out.println("La cuenta no tiene saldo suficiente");
                        break;
                    }
                    cuentaDestino = banco.getCuenta(operacion.getIdDestino()); //obtengo la cuenta de destino
                    cuentaDestino.depositar(operacion.getMonto()); //deposito en la cuenta
                    break;
            }
        }
    }
}
