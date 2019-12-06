package ejercicio6version2;

import ejercicio6Monitores.Barber;
import ejercicio6Monitores.BarberShop;
import ejercicio6Monitores.Client;

import java.util.concurrent.TimeUnit;

public class Barbero implements Runnable {

    private Barberia b;

    public Barbero(Barberia b){
        this.b = b;
    }

    @Override
    public void run() {
        while(true){
            Cliente c = b.llamarCliente();
            synchronized(b){
                if(c == null){
                    try {
                        System.out.println("Barbero: Me tiro una siesta, no hay nadie");
                        b.wait();
                        System.out.println("Barbero: Entro un cliente, se termino la joda");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        System.out.println("Barbero: voy a afeitar a " + c.getNombre() + " durante 5 segundos");
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    c.setAfeitado();
                }
            }
        }
    }

    public static void main(String[] args) {
        Barberia barberShop = new Barberia();
        Barbero barber = new Barbero(barberShop);
        Cliente c1 = new Cliente(barberShop,"Thomas");
        Cliente c2 = new Cliente(barberShop,"Juan");
        Cliente c3 = new Cliente(barberShop,"Dana");
        Cliente c4 = new Cliente(barberShop,"Cio");
        Cliente c5 = new Cliente(barberShop,"Maty");
        Cliente c6 = new Cliente(barberShop,"Jose");
        Cliente c7 = new Cliente(barberShop,"Rocio");
        Cliente c8 = new Cliente(barberShop,"Exe");
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


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // t0.setDaemon(true);
        t0.start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t8.start();
    }
}
