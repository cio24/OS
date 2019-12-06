package recuperatorio2018parcial2017;

import static java.lang.Thread.sleep;

public class Jugador implements Runnable {
    private AreaDeEspera areaDeEspera;
    private String name;
    private int tiempoDejuego;

    public Jugador(AreaDeEspera areaDeEspera, String name){
        this.areaDeEspera = areaDeEspera;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public synchronized void run(){
        System.out.println(getName()+": Me siento");
        areaDeEspera.sentarseSala(this);

        //se echa una siesta hasta que entren 4 jugadores
        try {
            System.out.println(getName()+": Espero a que me llamen para jugar");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        areaDeEspera.jugar(); //se duerme cerca de la consola hasta que estén todos

        tiempoDejuego=(int) Math.random()*10 + 1;
        System.out.println(getName() + ": voy a jugar durante " + tiempoDejuego + " segundos");
        try {
            wait(tiempoDejuego*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + ": me voy!");
        areaDeEspera.dejarJuego(this);
        if(!areaDeEspera.estanJugando()){ //si es el último jugador
            System.out.println(getName() + ": como soy el último notifico al Manager");
            synchronized(areaDeEspera){
                areaDeEspera.notify(); //se despierta al manager
            }
        }


    }
}
