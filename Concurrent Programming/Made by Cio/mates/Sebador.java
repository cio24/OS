package mates;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Sebador extends Persona implements Runnable {

    public final static int SEBADAS = 20;
    int restantes;

    Sebador(Sebador sebador, String nombre, Mate mate, Facturas facturas){
        super(null, nombre, facturas);
        this.mate = mate;
        restantes = SEBADAS;
    }

    private void llenarTermo(){
        //System.out.println("Sebador: LLENANDO TERMO!!!!");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void run(){
        while (true){
            for(Persona persona: rondaAmigos){
                if(restantes == 0)
                    llenarTermo();
                mate.echarAgua();
                restantes--;
                if(!persona.isComiendo()){
                    System.out.println("Sebador: " + persona.getNombre() + " tiene el mate!");
                    persona.darMate(mate);
                    mate = null;
                    while(mate == null){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Sebador: me devolvieron el mate");
                }
                else
                    System.out.println("Sebador: " + persona.getNombre() + " esta comiendo!");
            }
            if(tengoHambre())
                if(facturas.agarrarFacturas())
                    comer();
            mate.echarAgua();
            mate.tomar();
        }
    }

    public static void main(String[] args) {

        Facturas facturas = new Facturas(20);
        Mate mate = new Mate();
        ArrayList<Persona> rondaAmigos = new ArrayList<>();

        Sebador s = new Sebador(null,"Maty",mate,facturas);

        Persona p1 = new Persona(s,"juan",facturas);
        Persona p2 = new Persona(s,"Dana",facturas);
        Persona p3 = new Persona(s,"Cio",facturas);
        Persona p4 = new Persona(s,"Thom",facturas);
        Persona p5 = new Persona(s,"juanPablo",facturas);
        Persona p6 = new Persona(s,"Coria",facturas);

        rondaAmigos.add(p1);
        rondaAmigos.add(p2);
        rondaAmigos.add(p3);
        rondaAmigos.add(p4);
        rondaAmigos.add(p5);
        rondaAmigos.add(p6);

        s.setRondaAmigos(rondaAmigos);
        p1.setRondaAmigos(rondaAmigos);
        p2.setRondaAmigos(rondaAmigos);
        p3.setRondaAmigos(rondaAmigos);
        p4.setRondaAmigos(rondaAmigos);
        p5.setRondaAmigos(rondaAmigos);
        p6.setRondaAmigos(rondaAmigos);

        Thread t0 = new Thread(s,"0");
        Thread t1 = new Thread(p1,"1");
        Thread t2 = new Thread(p2,"2");
        Thread t3 = new Thread(p3,"3");
        Thread t4 = new Thread(p4,"4");
        Thread t5 = new Thread(p5,"5");
        Thread t6 = new Thread(p6,"7");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t0.start();
    }
}

