package Parcial2017;

public class Host implements Runnable {

    private Consola consola = new Consola();
    private SalaEspera sala;

    public Host(SalaEspera sala) {
        this.sala = sala;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Thread "+ Thread.currentThread().getName() + " Pidiendo siguiente bloque");
            Jugador[] jugadores = this.sala.siguientes();
            this.consola.setUsando();
            System.out.println("Thread "+ Thread.currentThread().getName() + " Dando joysticks");
            for (Jugador j: jugadores){
                System.out.println("Thread "+ Thread.currentThread().getName() + " Dando joystick a "+j.toString());
                j.darConsola(this.consola);
            }
            System.out.println("Thread "+ Thread.currentThread().getName() + " Esperando consola");
            this.consola.esperarLibre();
        }
    }
}
