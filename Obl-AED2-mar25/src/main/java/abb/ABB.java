package abb;

public class ABB<T extends Comparable<T>> implements IArbolBusqueda<T> {
    private NodoABB raiz;


    //innerclass

    private class NodoABB {
        private T dato;
        private NodoABB izq;
        private NodoABB der;

        public NodoABB(T dato) {
            this.dato = dato;
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

    public boolean pertenece(T x) {
        return perteneceRec(raiz, x);
    }

    private boolean perteneceRec(NodoABB actual, T valor) {
        if (actual == null) return false;
        int cmp = valor.compareTo(actual.dato);
        if (cmp == 0) return true;
        else if (cmp < 0) return perteneceRec(actual.izq, valor);
        else return perteneceRec(actual.der, valor);
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

    public void listarAscendente() {
        imprimirInorden(raiz);
        System.out.println();
    }

    private void imprimirInorden(NodoABB nodo) {
        if (nodo != null) {
            imprimirInorden(nodo.izq);
            System.out.print(nodo.dato + " ");
            imprimirInorden(nodo.der);
        }
    }

    public void listarDescendente() {
        imprimirDesc(raiz);
        System.out.println();
    }

    private void imprimirDesc(NodoABB nodo) {
        if (nodo != null) {
            imprimirDesc(nodo.der);
            System.out.print(nodo.dato + " ");
            imprimirDesc(nodo.izq);
        }
    }

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

