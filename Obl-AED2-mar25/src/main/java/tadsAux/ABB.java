package tadsAux;

import dominio.Viajero;

import java.util.Comparator;

public class ABB<T extends Comparable<T>> implements IArbolBusqueda<T> {
    private NodoABB raiz;

    private Comparator<T> comparador;



    public ABB() {
        this.comparador = null;
    }

    public ABB(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    private int comparar(T a, T b) {
        if (comparador != null)
            return comparador.compare(a, b);
        else
            return ((Comparable<T>) a).compareTo(b);
    }

    //innerclass

    private class NodoABB {
        private T dato;
        private NodoABB izq;
        private NodoABB der;

        public NodoABB(T dato) {
            this.dato = dato;
        }

        public T getDato() {
            return dato;
        }

        public NodoABB getIzq() {
            return izq;
        }

        public NodoABB getDer() {
            return der;
        }

        @Override
        public String toString() {
            return "NodoABB{" + dato + '}';
        }
    }


    public void insertar(T dato) {
        if (raiz == null) {
            raiz = new NodoABB(dato);
        } else {
            raiz = insertar(raiz, dato);
        }
    }

    private NodoABB insertar(NodoABB nodo, T dato) {
        if (nodo == null) {
            return new NodoABB(dato);
        }
        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) {
            nodo.izq = insertar(nodo.izq, dato);
        } else if (cmp > 0) {
            nodo.der = insertar(nodo.der, dato);
        }
        // Si cmp == 0, no se inserta duplicado (opcional: podrías permitirlo si querés)
        return nodo;
    }

    public boolean pertenece(T x) { // te devuelve true o false
        return perteneceRec(raiz, x);
    }

    private boolean perteneceRec(NodoABB actual, T valor) {
        if (actual == null) return false;
        int cmp = valor.compareTo(actual.dato);
        if (cmp == 0) return true;
        else if (cmp < 0) return perteneceRec(actual.izq, valor);
        else return perteneceRec(actual.der, valor);
    }

    public T buscar(T dato) { // te da el dato
        return buscarRec(raiz, dato);
    }

    private T buscarRec(NodoABB nodo, T dato) {
        if (nodo == null) return null;

        int cmp = comparar(dato, nodo.dato); //Cambie int cmp = dato.compareTo(nodo.dato)
                                            // porque el compareTo siempre iba a comparar por cedula
        if (cmp == 0) return nodo.dato;

        if (cmp < 0) return buscarRec(nodo.izq, dato);
        return buscarRec(nodo.der, dato);
    }

    public ResultadoBusqueda<T> buscarConComparaciones(T dato) {
        return buscarConComparaciones(raiz, dato, 0);
    }


    private ResultadoBusqueda<T> buscarConComparaciones(NodoABB nodo, T dato, int contador) {
        if (nodo == null) return new ResultadoBusqueda<>(null, contador);

        int cmp = comparar(dato, nodo.dato);
        contador++; // contamos la comparación hecha

        if (cmp == 0) return new ResultadoBusqueda<>(nodo.dato, contador);
        if (cmp < 0) return buscarConComparaciones(nodo.izq, dato, contador);
        return buscarConComparaciones(nodo.der, dato, contador);
    }


    public int altura() {
        return altura(raiz);
    }

    private int altura(NodoABB nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }

    public boolean equilibrado() {
        return equilibrado(raiz);
    }

    private boolean equilibrado(NodoABB nodo) {
        if (nodo == null) return true;
        return Math.abs(altura(nodo.izq) - altura(nodo.der)) <= 1 &&
                equilibrado(nodo.izq) &&
                equilibrado(nodo.der);
    }


    public String listarAscendente() {
        StringBuilder sb = new StringBuilder();
        listarAscendente(this.raiz, sb);
        return sb.toString();
    }

    private void listarAscendente(NodoABB nodo, StringBuilder sb) {
        if (nodo != null) {
            listarAscendente(nodo.izq, sb);
            if (sb.length() > 0) sb.append("|");
            sb.append(nodo.dato.toString());
            listarAscendente(nodo.der, sb);
        }
    }


    public String listarDescendente() {
        StringBuilder sb = new StringBuilder();
        imprimirDesc(this.raiz, sb);
        return sb.toString();
    }

    private void imprimirDesc(NodoABB nodo, StringBuilder sb) {
        if (nodo != null) {
            imprimirDesc(nodo.der, sb);
            if(sb.length()>0) sb.append("|");
            sb.append(nodo.dato.toString());
            imprimirDesc(nodo.izq, sb);
        }
    }

  /*  public String listarAscendente(NodoABB nodo, boolean esPrimero, String cadena){

        if(nodo != null){

            Viajero viajero = (Viajero) nodo.getDato();

            if(!esPrimero){
                cadena =
            }
        }
    }
*/
    public T borrarMinimo() {
        if (raiz == null) throw new RuntimeException("Árbol vacío");
        if (raiz.izq == null) {
            T dato = raiz.dato;
            raiz = raiz.der;
            return dato;
        }
        return borrarMinimo(raiz);
    }

    private T borrarMinimo(NodoABB nodo) {
        if (nodo.izq.izq == null) {
            T dato = nodo.izq.dato;
            nodo.izq = nodo.izq.der;
            return dato;
        }
        return borrarMinimo(nodo.izq);
    }

public boolean esVacia(){
        return raiz==null;
}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(raiz, "", sb);
        return sb.toString();
    }

    private void toStringRec(NodoABB nodo, String prefijo, StringBuilder sb) {
        if (nodo != null) {
            toStringRec(nodo.der, prefijo + "    ", sb);
            sb.append(prefijo).append(nodo.dato).append("\n");
            toStringRec(nodo.izq, prefijo + "    ", sb);
        }
    }
}

