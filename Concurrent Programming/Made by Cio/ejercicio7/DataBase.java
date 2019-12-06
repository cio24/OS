package ejercicio7;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

//CONSULTAAAA
//SI LOS ESCRITORES Y LOS LECTORES SON DE CLASES DIFERENTES, NO SE PUEDEN BLOQUEAR ENTRE SI SIN USAR SEMAFOROS
//DE LA CLASE DATABASE??

public class DataBase {
    private ArrayList<String> registers;
    public Semaphore mutex, writeControl;
    public int readers;
    private boolean firstReaderHaveAppear;

    public DataBase(ArrayList<String> registers){
        this.registers = registers;
        mutex = new Semaphore(1);
        writeControl = new Semaphore(1);
        firstReaderHaveAppear = false;
        readers = 0;
    }

    public String read(int i){
        if((i >= 0) && (i < registers.size())) {
            writeControl.release();
            return registers.get(i);
        }
        return null;
    }

    public void write(int i, String newValue){
        if((i >= 0) && (i < registers.size())) {
            registers.remove(i);
            registers.add(i, newValue);
        }
    }
}
