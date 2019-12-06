package Parcial2017;

public class Consola {

    private boolean libre = true;
    private int jugando = 0;

    public synchronized void salir(Jugador jugador) {
        this.jugando--;
        if(this.jugando == 0){
            libre = true;
            this.notifyAll();
        }
    }


    public synchronized void tomarJoystick() {
        if(this.jugando==0)
            System.out.println("Thread "+ Thread.currentThread().getName() + " Configurando la consola");
        this.jugando++;
        while (this.jugando < 4) {//espera a los otros 3
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();//cuando llega el 4to, notifica a los demas
    }

    public synchronized void setUsando() {
        this.libre = false;
    }

    public synchronized void esperarLibre() {
        while (!this.libre) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
