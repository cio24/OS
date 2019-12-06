package ejercicio11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Miembro implements Runnable {
    Almacen almacen;
    ArrayList<Miembro> miembros;
    ArrayList<Miembro> vinosAProbar;
    int losQueNoProbaronMiVino;
    String nombre;
    int vinosPreparados;

    Miembro(Almacen almacen, String nombre){
        this.almacen = almacen;
        this.vinosAProbar = new ArrayList<>();
        this.nombre = nombre;
        vinosPreparados = 0;
        losQueNoProbaronMiVino = 0;
    }

    public void setMiembros(ArrayList<Miembro> miembros) {
        this.miembros = miembros;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void mezclar(){
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public synchronized void esperarCuatroSemanas(){
        /*
        long actual = System.currentTimeMillis();
        long finish = 10000 + actual;
        while (finish > actual ) {
            while (!vinosAProbar.isEmpty()){
                System.out.println(this.getNombre()+": probando vino de " + this.vinosAProbar.get(0).getNombre());
                this.vinosAProbar.remove(0).vinoProbado();
            }
            try {
                this.wait(finish - actual);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            actual = System.currentTimeMillis();
        }

         */
    }

    public synchronized void probarVinoDe(Miembro m){
        this.vinosAProbar.add(m);
        notify();
    }

    public synchronized void vinoProbado() {
        losQueNoProbaronMiVino--;
        notifyAll();
    }

    public boolean equals(Miembro m){
        return this.getNombre().equals(m.getNombre());
    }

    @Override
    public synchronized void run(){
        while(true){
            while (losQueNoProbaronMiVino > 0 && vinosAProbar.isEmpty()) {
                try {
                    System.out.println(this.getNombre()+": tengo que esperar a que prueben mi vino y yo no tengo vinos para probar");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (!this.vinosAProbar.isEmpty()){
                synchronized (this){
                    System.out.println(this.getNombre()+": probando vino de "+ this.vinosAProbar.get(0).getNombre());
                    this.vinosAProbar.remove(0).vinoProbado();
                }
            }

            if(vinosPreparados == 2){
                System.out.println(getNombre() +": YA PREPARE 2 VINACHOS, ME PIRO VAMPIROOOOO");
                break;
            }

            if(this.losQueNoProbaronMiVino == 0) {
                System.out.println(this.getNombre()+": ya probaron mi vino, voy a empezar a hacer vino");
                almacen.getComponentesPrimerEtapa();
                this.mezclar();
                almacen.addComponentesPrimerEtapa();
                this.esperarCuatroSemanas();
                almacen.getJarra();
                almacen.addJarras();
                System.out.println(this.getNombre()+": termine de preparar el vino");
                vinosPreparados++;
                for (Miembro miembro : miembros) {
                    if(!miembro.equals(this)){
                        System.out.println(this.getNombre() + ": estoy diciendole al miembro " + miembro.getNombre() + " que pruebe mi vino");
                        miembro.probarVinoDe(this);
                        this.losQueNoProbaronMiVino++;


                    }
                }
            }
        }
    }
}
