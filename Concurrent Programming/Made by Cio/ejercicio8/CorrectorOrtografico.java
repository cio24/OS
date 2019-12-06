package ejercicio8;

import java.util.HashSet;
import java.util.Set;

public class CorrectorOrtografico {
    private Set<String> palabras;

    public CorrectorOrtografico(Set<String> palabras){
        this.palabras = palabras;
    }

    public synchronized void cargarPalabras(Set<String> palabras){
        this.palabras.addAll(palabras);
    }

    public synchronized void eliminarPalabras(Set<String> palabras){
        this.palabras.removeAll(palabras);
    }

    public synchronized Set<String> verificarPalabras(Set<String> palabras){
        HashSet<String> palabrasAusentes = new HashSet<String>();
        palabrasAusentes.addAll(palabras);
        palabrasAusentes.removeAll(this.palabras);
        return palabrasAusentes;
    }

}
