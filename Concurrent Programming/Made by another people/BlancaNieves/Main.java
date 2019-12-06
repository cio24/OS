package BlancaNieves;


public class Main {
    public static void main(String[] args) {
    	Casa c = new Casa();
        BlancaNieve B= new BlancaNieve(c);
        Thread tb = new Thread(B, "Blanca");
        tb.start();
        for (int i=0; i<7; i++){
            String enanoN = "Enano_"+i;
            Enanito e = new Enanito(c);//crea un jugador
            Thread te = new Thread(e, enanoN);//crea un thrad que utiliza el objeto de la clase jugador
            te.start();
        }
    }
}
