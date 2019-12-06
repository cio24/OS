package ejercicio7;

import java.sql.DatabaseMetaData;

public class Writer implements Runnable{
    private DataBase dataBase;

    public Writer(DataBase dataBase){
        this.dataBase = dataBase;
    }

    @Override
    public void run() {
        while(true){
            try { dataBase.writeControl.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
            dataBase.write(0,"Hello World!");
            dataBase.writeControl.release();
        }
    }
}
