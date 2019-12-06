package ejercicio6version2;

public class Cliente implements Runnable{
    private Barberia b;
    private boolean afeitado;
    private String nombre;

    public Cliente(Barberia b, String nombre){
        this.b = b;
        this.nombre = nombre;
        afeitado = false;
    }

    public String getNombre(){
        return this.nombre;
    }

    public synchronized void setAfeitado(){
        this.afeitado = true;
        System.out.println(this.getNombre() + ": He sido afeitado!");
        this.notify();
    }

    public boolean getAfeitado(){
        return this.afeitado;
    }

    @Override
    public synchronized void run() {
        if(b.entrar(this)){
            try {
                System.out.println(this.getNombre() + ": consegui asiento!");
                this.wait(); //espera hasta que le corten el pelo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{ //si no entra se va y si entra le cortan el pelo
            System.out.println(this.getNombre() + ": Esta lleno!");
        }
    }
}
