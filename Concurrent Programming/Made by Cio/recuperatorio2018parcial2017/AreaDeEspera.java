package recuperatorio2018parcial2017;

import java.util.ArrayList;

public class AreaDeEspera{
    private ArrayList<Jugador> jugadoresEsperando;
    private ArrayList<Jugador> listosParajugar;
    final static int MAX_JUGADORES = 4;

    public AreaDeEspera(){
        jugadoresEsperando = new ArrayList<>();
        listosParajugar = new ArrayList<>();
    }

    public synchronized void sentarseSala(Jugador j){
        jugadoresEsperando.add(j);
        if(jugadoresEsperando.size() >= MAX_JUGADORES && !this.estanJugando()){
            System.out.println("Un jugador le avisa al manager que hay 4 jugadores y nadie esta jugando!");
            notify(); //se notifica al manager por si hay 4 jugadores
        }
    }

    public synchronized ArrayList<Jugador> obtenerJugadores(){
         //el manager se duerme si no hay al menos 4 jugadores.
        while(jugadoresEsperando.size() < MAX_JUGADORES){
            try {
                System.out.println("Manager: todavía no hay 4 jugadores");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //como están 4 o más jugadores, el manager los pasa a al arreglo de listos para jugar
        System.out.println("Manager: ya están todos, los llamo!");
        for(int i = 0; i < MAX_JUGADORES; i++){
            Jugador j = jugadoresEsperando.remove(0);
            listosParajugar.add(j);
            synchronized (j){
                j.notify(); //se desíerta al jugador
            }
        }
        return null;
    }

    public void jugar(){
        synchronized (listosParajugar) {
            if(listosParajugar.size() == MAX_JUGADORES){
                System.out.println("el cuarto jugador le avisa a todos que empiecen a jugar");
                listosParajugar.notifyAll();
            }
            while(listosParajugar.size() < MAX_JUGADORES) {
                try {
                    System.out.println("Un jugador espera cerca de la consola a que esten todos");
                    listosParajugar.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean estanJugando(){
        synchronized(listosParajugar){
            return !listosParajugar.isEmpty();
        }
    }

    public void dejarJuego(Jugador jugador){
        synchronized (listosParajugar) {
            listosParajugar.remove(jugador);
        }
    }
}
