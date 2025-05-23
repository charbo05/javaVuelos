package interfaz;

import dominio.Ciudad;

public interface ILista <T extends Comparable <T>> {
    void insertar(T dato);
    void borrar(T dato);
    int largo();
    boolean existe(T dato);
    T recuperar(T dato);
    boolean esVacia();
    boolean esLlena();
    void imprimirDatos();



}
