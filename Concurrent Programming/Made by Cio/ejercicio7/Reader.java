package ejercicio7;

public class Reader implements Runnable {

    private DataBase dataBase;

    public Reader(DataBase dataBase){
        this.dataBase = dataBase;
    }

    @Override
    public void run() {
        while(true){
            try { dataBase.mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
            if(++dataBase.readers == 1){
                try { dataBase.writeControl.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
                dataBase.mutex.release();
                dataBase.read(0);
                try { dataBase.mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
                if(--dataBase.readers == 0)
                    try { dataBase.writeControl.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
                dataBase.mutex.release();
            }
        }
    }
}
