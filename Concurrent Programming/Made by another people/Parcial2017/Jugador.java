package Parcial2017;


public class Jugador implements  Runnable {

    private SalaEspera sala;
    private String nombre;
    private Consola consola=null;

    public Jugador(SalaEspera s, String nombre) {
        this.sala = s;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        System.out.println("Thread "+ Thread.currentThread().getName() + " Entrando");
        this.sala.esperar(this);
        synchronized (this) {
            while (this.consola == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Thread "+ Thread.currentThread().getName() + " Tomando Joystick");
        consola.tomarJoystick();
        System.out.println("Thread "+ Thread.currentThread().getName() + " Jugando");
        try {
            Thread.sleep((long)(1000 * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.consola.salir(this);
        System.out.println("Thread "+ Thread.currentThread().getName() + " Me fui");
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public synchronized void darConsola(Consola consola) {
        this.consola = consola;
        this.notify();
    }
}

