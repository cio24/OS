package ejercicio11;

public class Almacen {
    int estacionesDeMezcla, jarras, unidadesFermDesc, envasesJugo, paquetesLevadura;

    Almacen() {
        estacionesDeMezcla = 2;
        jarras = 6;
        unidadesFermDesc = 7;
        envasesJugo = 500;
        paquetesLevadura = 200;
    }

    public synchronized boolean getComponentesPrimerEtapa() {
        while (estacionesDeMezcla <= 0 || jarras <= 1 || unidadesFermDesc <= 0 || envasesJugo <= 1 || paquetesLevadura <= 0) {
            try {
                System.out.println("me duermo xq no estan todos los componentes");
                this.wait();
                System.out.println("alguien me desperto, así que liberaron componentes");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        estacionesDeMezcla--;
        unidadesFermDesc--;
        jarras--;
        envasesJugo -= 2;
        paquetesLevadura--;
        return true;
    }

    public synchronized void addComponentesPrimerEtapa() {
        estacionesDeMezcla++;
        unidadesFermDesc++;
        notifyAll();
    }

    public synchronized boolean getJarra() {
        while (jarras <= 0) {
            try {
                System.out.println("me duermo xq no hay jarras libres");
                this.wait();
                System.out.println("me despertaron, alguien debe haber devuelto componentes");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        jarras--;
        return true;
    }

    public synchronized void addJarras(){
        jarras+=2;
        notifyAll();
    }

    //metodos utilizados por el administrador

    public synchronized void addEnvasesJugo(int n) {
        envasesJugo += n;
        notifyAll();
    }

    public synchronized void addLevadura(int n) {
        paquetesLevadura += n;
        notifyAll();
    }
}
