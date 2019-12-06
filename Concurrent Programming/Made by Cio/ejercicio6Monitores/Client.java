package ejercicio6Monitores;

import java.util.concurrent.Semaphore;

public class Client implements Runnable {
    private String name;
    private BarberShop barberShop;
    private boolean ready = false;

    public Client(BarberShop barberShop, String name){
        this.barberShop = barberShop;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public synchronized void shaveBeard(){
        if(this.getName() == "Dana")
            System.out.println("Client " + this.getName() + ": te dije solo las puntas hijo de puta!");
        else
            System.out.println("Client " + this.getName() + ": nice cut, goodbye!");
        ready = true;
        notify();
    }

    @Override
    public void run(){
        try {
            if(barberShop.takeSeat(this)){
                synchronized (this) {
                    while (!ready)
                        this.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}