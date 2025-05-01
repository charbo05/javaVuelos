package tadsAux;

public class ResultadoBusqueda<T> {
    private final T dato;
    private final int comparaciones;

    public ResultadoBusqueda(T dato, int comparaciones) {
        this.dato = dato;
        this.comparaciones = comparaciones;
    }

    public T getDato() {
        return dato;
    }

    public T setDato(T dato) { return dato;}

    public int getComparaciones() {
        return comparaciones;
    }

    public int setComparaciones() { return comparaciones;}



}
