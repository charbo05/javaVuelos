package tadsAux;

public interface IArbolBusqueda <T extends Comparable <T>> {

    void insertar(T dato);
    boolean pertenece(T dato);
    int altura();
    boolean equilibrado();
    String listarAscendente();
    String listarDescendente();
    T borrarMinimo();
    T buscar(T dato);
    ResultadoBusqueda<T> buscarConComparaciones(T dato);
    boolean esVacia();
}
