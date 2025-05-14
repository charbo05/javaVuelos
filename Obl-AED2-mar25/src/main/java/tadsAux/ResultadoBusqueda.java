package tadsAux;

/*Clase auxiliar genérica para encapsular dos elementos fundamentales que se obtienen al realizar una búsqueda*/
public class ResultadoBusqueda<T> {

    private T dato; // El resultado (puede ser un vuelo, una ciudad, etc.)
    private int comparaciones; // Número de comparaciones realizadas en la búsqueda

    public ResultadoBusqueda(T dato, int comparaciones) {
        this.dato = dato;
        this.comparaciones = comparaciones;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public void setComparaciones(int comparaciones) {
        this.comparaciones = comparaciones;

    }

}
