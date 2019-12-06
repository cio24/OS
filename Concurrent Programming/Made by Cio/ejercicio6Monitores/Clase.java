package ejercicio6Monitores;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Clase {

    public Clase() {
    }

    public void algo(){
        try {
            System.out.println("se mantiene ocupado durante 5 segundos");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
