package tadsAux;

public class ResultadoBusqueda<T> {
    private T dato;
    private int comparaciones;

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
