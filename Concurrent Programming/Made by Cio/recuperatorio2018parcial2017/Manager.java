package recuperatorio2018parcial2017;

import java.awt.geom.Area;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Manager implements Runnable{
    AreaDeEspera areaDeEspera;
    private ArrayList<Jugador> listosParaJugar;
    public Manager(AreaDeEspera areaDeEspera){
        this.areaDeEspera = areaDeEspera;
    }

    @Override
    public void run() {
        while(true){
            listosParaJugar = areaDeEspera.obtenerJugadores();
            for(Jugador jugador: listosParaJugar){
                areaDeEspera.jugar();
            }
            while(areaDeEspera.estanJugando()){
                synchronized (areaDeEspera){ //se duerme en el area de espera hasta que terminen de jugar todos
                    try {
                        areaDeEspera.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        AreaDeEspera areaDeEspera = new AreaDeEspera();
        Jugador j1 = new Jugador(areaDeEspera, "Juan");
        Jugador j2 = new Jugador(areaDeEspera, "Dana");
        Jugador j3 = new Jugador(areaDeEspera, "Cio");
        Jugador j4 = new Jugador(areaDeEspera, "Thom");
        Jugador j5 = new Jugador(areaDeEspera, "Maty");
        Jugador j6 = new Jugador(areaDeEspera, "Daniel");
        Jugador j7 = new Jugador(areaDeEspera, "Campos");
        Jugador j8 = new Jugador(areaDeEspera, "Analia");
        Manager manager = new Manager(areaDeEspera);

        Thread t1 = new Thread(j1,"t1");
        Thread t2 = new Thread(j2,"t2");
        Thread t3 = new Thread(j3,"t3");
        Thread t4 = new Thread(j4,"t4");
        Thread t5 = new Thread(j5,"t5");
        Thread t6 = new Thread(j6,"t6");
        Thread t7 = new Thread(j7,"t7");
        Thread t8 = new Thread(j8,"t8");
        Thread t9 = new Thread(manager,"t9");

        t9.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t6.start();
        t7.start();
        t8.start();



    }
}
