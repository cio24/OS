package Parcial2017;

import java.util.LinkedList;
import java.util.List;

public class SalaEspera {
    private List<Jugador> jugadores = new LinkedList<>();
    private int bloque = 4;

    //Llamado por jugador
    public synchronized void esperar(Jugador jugador) {
        this.jugadores.add(jugador);
        this.notify(); //despierta al de siguientes
    }

    //llamado por host
    public synchronized Jugador[] siguientes() {
        while (this.jugadores.size()<bloque){
            try {
                this.wait(); //despertado por espera
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Jugador[] res= new Jugador[this.bloque];
        for (int i=0; i<this.bloque;i++)
            res[i] = this.jugadores.remove(0);
        return res;
    }
}
