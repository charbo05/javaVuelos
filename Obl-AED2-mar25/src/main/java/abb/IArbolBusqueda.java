package abb;

public interface IArbolBusqueda <T extends Comparable <T>> {

    void insertar(T dato);
    boolean pertenece(T dato);
    int altura();
    boolean equilibrado();
    void listarAscendente();
    void listarDescendente();
    T borrarMinimo();
    T buscar(T dato);

}
