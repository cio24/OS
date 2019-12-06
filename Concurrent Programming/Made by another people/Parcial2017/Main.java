package Parcial2017;


public class Main {

    public static void main(String[] args) {
        SalaEspera s = new SalaEspera();
        Host h = new Host(s);
        Thread th = new Thread(h, "HOST");
        th.setDaemon(true);//Si todos los threads no daemon terminan termina el programa
        th.start();
        for (int i=0; i<40; i++){
            String jugadorN = "JUGADOR_"+i;
            Jugador j = new Jugador(s, jugadorN);//crea un jugador
            Thread t = new Thread(j, jugadorN);//crea un thrad que utiliza el objeto de la clase jugador
            t.start();
        }
    }
}
