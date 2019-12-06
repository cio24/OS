package ejercicio11;

import java.awt.datatransfer.MimeTypeParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Administrador implements Runnable{
    Almacen almacen;
    Administrador(Almacen almacen){
        this.almacen = almacen;
    }

    public void addLevadura(int n){
        almacen.addLevadura(n);
    }

    public void addEnvasesJugo(int n){
        almacen.addEnvasesJugo(n);
    }

    @Override
    public void run(){
        while(true){
            almacen.addLevadura(20);
            almacen.addEnvasesJugo(15);
            //esperar 2 semanas
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public static void main(String[] args) {
        Almacen a = new Almacen();
        Miembro m1 = new Miembro(a,"m1");
        Miembro m2 = new Miembro(a,"m2");
        Miembro m3 = new Miembro(a,"m3");
        Miembro m4 = new Miembro(a,"m4");
        Miembro m5 = new Miembro(a,"m5");
        Miembro m6 = new Miembro(a,"m6");
        Miembro m7 = new Miembro(a,"m7");
        Miembro m8 = new Miembro(a,"m8");
        ArrayList<Miembro> miembros = new ArrayList<>();
        miembros.add(m1);
        miembros.add(m2);
        miembros.add(m3);
        /*
        miembros.add(m4);
        miembros.add(m5);
        miembros.add(m6);
        miembros.add(m7);
        miembros.add(m8);

         */
        m1.setMiembros(miembros);
        m2.setMiembros(miembros);
        m3.setMiembros(miembros);
        /*
        m4.setMiembros(miembros);
        m5.setMiembros(miembros);
        m6.setMiembros(miembros);
        m7.setMiembros(miembros);
        m8.setMiembros(miembros);

         */

        Thread t1 = new Thread(m1,"t1");
        Thread t2 = new Thread(m2,"t2");
        Thread t3 = new Thread(m3,"t3");
        /*
        Thread t4 = new Thread(m4,"t4");
        Thread t5 = new Thread(m5,"t5");
        Thread t6 = new Thread(m6,"t6");
        Thread t7 = new Thread(m7,"t7");
        Thread t8 = new Thread(m8,"t8");

         */


        t1.start();
        t2.start();
        t3.start();
        /*
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();

         */
    }
}
