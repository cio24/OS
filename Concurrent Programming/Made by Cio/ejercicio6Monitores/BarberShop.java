package ejercicio6Monitores;

import java.util.ArrayList;

public class BarberShop {
    private ArrayList<Client> clients;
    public static final int MAX_CHAIRS = 3;

    public BarberShop(){
        clients = new ArrayList<>();
    }

    private boolean clientsWaiting(){
        return clients.size() > 0;
    }
    private boolean areFreeSeats(){
        return clients.size() < MAX_CHAIRS;
    }

    public synchronized Client callNextClient() throws InterruptedException {
        while(clients.size() < 0){
            wait();
        }
        return clients.remove(0);
    }

    public synchronized  boolean takeSeat(Client c) throws InterruptedException {
        if(areFreeSeats()) {
            clients.add(c);
            notify();
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        BarberShop barberShop = new BarberShop();
        Barber barber = new Barber(barberShop);
        Client c1 = new Client(barberShop,"Thomas");
        Client c2 = new Client(barberShop,"Juan");
        Client c3 = new Client(barberShop,"Dana");
        Client c4 = new Client(barberShop,"Cio");
        Client c5 = new Client(barberShop,"Maty");
        Client c6 = new Client(barberShop,"Jose");
        Client c7 = new Client(barberShop,"Rocio");
        Client c8 = new Client(barberShop,"Exe");
        Thread t0 = new Thread(barber,"t0");
        Thread t1 = new Thread(c1,"t1");
        Thread t2 = new Thread(c2,"t2");
        Thread t3 = new Thread(c3,"t3");
        Thread t4 = new Thread(c4,"t4");
        Thread t5 = new Thread(c5,"t5");
        Thread t6 = new Thread(c6,"t6");
        Thread t7 = new Thread(c7,"t7");
        Thread t8 = new Thread(c8,"t8");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t0.setDaemon(true);
        t0.start();
    }
}

