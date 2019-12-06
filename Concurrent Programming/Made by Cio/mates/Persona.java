package mates;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Persona implements Runnable{

    protected ArrayList<Persona> rondaAmigos;
    private Sebador sebador;
    protected Mate mate;
    protected Facturas facturas;
    private String nombre;
    private boolean comiendo;

    public Persona( Sebador sebador, String nombre, Facturas facturas){
        this.rondaAmigos = null;
        this.sebador = sebador;
        this.nombre = nombre;
        this.facturas = facturas;
        comiendo = false;
        mate = null;
    }

    protected void setRondaAmigos(ArrayList<Persona> rondaAmigos){
        this.rondaAmigos = rondaAmigos;
    }

    public boolean isComiendo(){
        return comiendo;
    }

    public String getNombre(){
        return this.nombre;
    }

    protected synchronized void darMate(Mate mate){
        notify();
        this.mate = mate;
    }

    private void devolverMate(Persona persona){
        persona.darMate(mate);
        this.mate = null;
    }

    protected void comer(){
        comiendo = true;
        System.out.println(getNombre()+": comiendo...");
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        comiendo = false;
    }

    protected boolean tengoHambre(){
        double n = Math.random()*100;
       return n < 50;
    }

    private void devolverACualquiera(Mate mate){
        int n = (int) (Math.random()*rondaAmigos.size());
        System.out.println(getNombre()+": Se lo voy a dar al nro " + n);
        if(rondaAmigos.get(n).getNombre().equals(this.getNombre())){
            System.out.println(getNombre()+": Me lo quedo yo? jaja");
            devolverACualquiera(mate);
        }
        devolverMate(rondaAmigos.get(n));
    }

    private boolean estoyDespistado(){
        double n = Math.random()*100;
        return n < 50;
    }

    @Override
    public synchronized void run() {
        while(true){
            while(mate == null){
                try {
                    System.out.println(getNombre()+ ": Espero a mi turno del mate");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(mate.tieneAgua()){
                mate.tomar();
                if(estoyDespistado())
                    devolverACualquiera(this.mate);
                else
                    devolverMate(sebador);
            }
            else{
                System.out.println(getNombre()+ ": Me dieron un mate vacio, se lo doy al sebador");
                devolverMate(sebador);
            }
            if(tengoHambre())
                if(facturas.agarrarFacturas())
                    comer();
        }
    }
}


