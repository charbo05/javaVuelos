package tadsAux;

import dominio.Ciudad;
import interfaz.ILista;

import java.util.Comparator;
import java.util.function.Function;

public class ListaImpl<T extends Comparable<T>> implements ILista<T> {

    protected NodoLista<T> inicio;
    protected int largo;
    private Comparator<T> comparador;

    public ListaImpl() {
        this.inicio = null;
        this.largo = 0;
        this.comparador = null;
    }



    public ListaImpl(Comparator<T> comparador) {
        this.comparador = comparador;
    }



    private int comparar(T a, T b) {
        if (comparador != null)
            return comparador.compare(a, b);
        else
            return ((Comparable<T>) a).compareTo(b);
    }

    @Override
    public void insertar(T dato) {
        inicio = new NodoLista<T>(dato, inicio);
        largo++;
    }

    @Override
    public void borrar(T dato) {
        {
            if (inicio == null) return;

            // Si el dato a borrar está al principio
            if (inicio.getDato().equals(dato)) {
                inicio = inicio.getSig();
                largo--;
                return;
            }

            NodoLista<T> anterior = inicio;
            NodoLista<T> actual = inicio.getSig();

            while (actual != null) {
                if (actual.getDato().equals(dato)) {
                    anterior.setSig(actual.getSig());
                    largo--;
                    return;
                }
                anterior = actual;
                actual = actual.getSig();
            }
        }

    }

    @Override
    public int largo() {
        return largo;
    }

    @Override
    public boolean existe(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }

    @Override
    public T recuperar(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return aux.getDato();
            }
            aux = aux.getSig();
        }
        return null;
    }

    @Override
    public boolean esVacia() {
        return largo == 0;
    }

    @Override
    public boolean esLlena() {
        return false;
    }

    @Override
    public void imprimirDatos() {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getSig() != null) {
                System.out.print(aux.getDato() + " -> ");
            } else {
                System.out.print(aux.getDato());
            }
            aux = aux.getSig();
        }
        System.out.println();
    }

    public void imprimirDatosV2(NodoLista<T> nodo) {
        if (nodo != null) {
            System.out.println(nodo.getDato());
            imprimirDatosV2(nodo.getSig());
        }
    }


    class NodoLista<T> {
        private T dato;
        private NodoLista<T> sig;

        public NodoLista(T dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoLista(T dato, NodoLista<T> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public NodoLista<T> getSig() {
            return sig;
        }

        public void setSig(NodoLista<T> sig) {
            this.sig = sig;
        }

        @Override
        public String toString() {
            return dato.toString();
        }

    }

    //-------------------------------------------------------

    public String toStringPersonalizado(Function<T, String> formateador) {
        StringBuilder sb = new StringBuilder();
        NodoLista<T> actual = inicio;

        while (actual != null) {
            sb.append(formateador.apply(actual.getDato())).append("|");
            actual = actual.getSig();
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1); // eliminar el último "|"
        }

        return sb.toString();
    }


    private NodoLista<T> insertarOrdenado(NodoLista<T> aux, NodoLista<T> nuevoNodo, Comparator<? super T> comparador) {
        nuevoNodo.setSig(null); // Aseguramos que el nuevo nodo apunte a null inicialmente

        if (aux == null || comparador.compare(nuevoNodo.getDato(), aux.getDato()) <= 0) {
            nuevoNodo.setSig(aux);
            return nuevoNodo;
        }

        NodoLista<T> current = aux;
        while (current.getSig() != null && comparador.compare(nuevoNodo.getDato(), current.getSig().getDato()) > 0) {
            current = current.getSig();
        }
        nuevoNodo.setSig(current.getSig());
        current.setSig(nuevoNodo);

        return aux;
    }

    public void ordenarPor(Comparator<? super T> comparador) {
        if (inicio == null || inicio.getSig() == null) {
            return; // La lista está vacía o tiene un solo elemento, ya está ordenada
        }

        NodoLista<T> aux = null;
        NodoLista<T> current = inicio;

        while (current != null) {
            NodoLista<T> next = current.getSig();
            aux = insertarOrdenado(aux, current, comparador);
            current = next;
        }
        inicio = aux;
    }

    public boolean buscarConexion(Ciudad ciudadA, Ciudad ciudadB) {

        if (ciudadA == null || ciudadB == null) {
            return false;
        }

        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (((Ciudad) aux.getDato()).getCodigo().equals(ciudadB.getCodigo())) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }

    public ResultadoBusqueda<T> buscarConComparaciones(T dato) {
        int comparaciones = 0;
        ResultadoBusqueda<T> resultado = new ResultadoBusqueda<>(null, 0); // Inicialmente null

        NodoLista<T> nodoActual = inicio;

        while (nodoActual != null) {
            comparaciones++;

            if (nodoActual.dato.equals(dato)) {
                resultado.setDato(nodoActual.dato); // Se encontró el dato real de la lista
                resultado.setComparaciones(comparaciones);
                return resultado;
            }

            nodoActual = nodoActual.sig;
        }

        // No se encontró
        resultado.setComparaciones(comparaciones);
        return resultado;
    }




}
