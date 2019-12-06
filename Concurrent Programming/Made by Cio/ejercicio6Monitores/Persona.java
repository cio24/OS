package ejercicio6Monitores;

import java.util.concurrent.TimeUnit;


public class Persona implements Runnable {
    private String nombre;
    private Clase c;

    public Persona(String nombre, Clase c){
        this.nombre = nombre;
        this.c = c;
    }

    @Override
    public void run() {
        c.algo();
    }

    public static void main(String[] args) {
        Clase c = new Clase();
        Persona p1 = new Persona("Juan", c);
        Persona p2 = new Persona("Juan", c);
        Thread t1 = new Thread(p1,"a");
        Thread t2 = new Thread(p2,"a");
        t1.start();
        t2.start();
    }
}
