package ejercicio6Semaphores;

import java.util.concurrent.TimeUnit;

public class Barber implements Runnable {
    private BarberShop barberShop;

    public Barber(BarberShop barberShop){
        this.barberShop = barberShop;
    }

    public void shave(Client c){
        System.out.println("Barber: I'm shaving the client " + c.getName());
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        c.shaveBeard();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Client c = barberShop.callNextClient();
                this.shave(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
