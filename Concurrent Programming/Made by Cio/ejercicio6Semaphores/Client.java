package ejercicio6Semaphores;

import java.util.concurrent.Semaphore;

public class Client implements Runnable {
    private String name;
    private BarberShop barberShop;
    private boolean haveBeard;
    private Semaphore customersControl; //if there's no customers then the barber goes to sleep

    public Client(BarberShop barberShop, String name){
        this.barberShop = barberShop;
        this.name = name;
        customersControl = new Semaphore(0);
        this.haveBeard = true;
    }

    public String getName(){
        return this.name;
    }

    public void shaveBeard(){
        this.haveBeard = false;
        if(this.getName() == "Dana")
            System.out.println("Client " + this.getName() + ": te dije solo las puntas hijo de puta!");
        else
            System.out.println("Client " + this.getName() + ": nice cut, goodbye!");
        customersControl.release();
    }

    @Override
    public void run(){
        try {
            if(barberShop.takeSeat(this)){
                customersControl.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
