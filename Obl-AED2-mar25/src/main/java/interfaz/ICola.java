package interfaz;

public interface ICola<T extends Comparable<T>> {

    public boolean EsVacia();

    public void encolar(T n);

    public void desencolar();

    public void vaciar();

    public void mostrar();

    public int cantElementos();

//    public NodoSE obtenerPrimerElemento();

    public boolean existeElemento(T n);

}
