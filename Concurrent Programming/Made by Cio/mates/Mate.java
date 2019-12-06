package mates;

import static java.lang.Thread.sleep;

public class Mate {
    private boolean tieneAgua;

    Mate(){
        tieneAgua= false;
    }

    public void echarAgua(){
        tieneAgua = true;
    }

    public boolean tieneAgua(){
        return tieneAgua;
    }

    public void tomar(){
        System.out.println("ruido de mate durante 4 segundos...");
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tieneAgua = false;
    }
}
