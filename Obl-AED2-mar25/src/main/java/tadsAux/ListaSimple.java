package tadsAux;

import java.util.Comparator;

public class ListaSimple<T>  {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;

    public ListaSimple() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }

    // Inner class
    private class Nodo<T> {
        protected T dato;
        protected Nodo<T> siguiente;

        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public Nodo<T> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<T> siguiente) {
            this.siguiente = siguiente;
        }
    }

//    @Override
//    public Nodo<T> getInicio() {
//        return inicio;
//    }
//
//    @Override
//    public Nodo<T> getFin() {
//        return fin;
//    }

    @Override
    public void Adicionar(T x) {

    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void agregarInicio(T n) {
        Nodo<T> nuevo = new Nodo<>(n);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        cantElementos++;
    }

    @Override
    public void agregarFinal(T n) {
        if (esVacia()) {
            agregarInicio(n);
        } else {
            Nodo<T> nuevo = new Nodo<>(n);
            fin.siguiente = nuevo;
            fin = nuevo;
            cantElementos++;
        }
    }

    @Override
    public void Insertar(T x, int pos) throws Exception {

    }

    @Override
    public T Obtener(int pos) throws Exception {
        return null;
    }

    @Override
    public void Eliminar(int pos) throws Exception {

    }

    @Override
    public void borrarInicio() {
        if (!esVacia()) {
            if (inicio.siguiente == null) {
                inicio = null;
                fin = null;
            } else {
                Nodo<T> aBorrar = inicio;
                inicio = inicio.siguiente;
                aBorrar.siguiente = null;
            }
            cantElementos--;
        }
    }

    @Override
    public void borrarFin() {
        if (!esVacia()) {
            if (inicio.siguiente == null) {
                borrarInicio();
            } else {
                Nodo<T> aux = inicio;
                while (aux.siguiente != fin) {
                    aux = aux.siguiente;
                }
                fin = aux;
                aux.siguiente = null;
                cantElementos--;
            }
        }
    }

    @Override
    public void vaciar() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }

    @Override
    public void mostrar() {
        if (!esVacia()) {
            Nodo<T> aux = inicio;
            while (aux != null) {
                System.out.println(aux.dato.toString());
                aux = aux.siguiente;
            }
            System.out.println();
        } else {
            System.out.println("Lista vacía.");
        }
    }

    @Override
    public void borrarElemento(T n) {
        if (!esVacia()) {
            if (inicio.dato.equals(n)) {
                borrarInicio();
            } else {
                Nodo<T> aux = inicio;
                while (aux.siguiente != null && !aux.siguiente.dato.equals(n)) {
                    aux = aux.siguiente;
                }
                if (aux.siguiente != null) {
                    Nodo<T> aBorrar = aux.siguiente;
                    aux.siguiente = aBorrar.siguiente;
                    aBorrar.siguiente = null;
                    cantElementos--;
                }
            }
        }
    }

    @Override
    public int cantElementos() {
        return cantElementos;
    }
//
//    @Override
//    public Nodo<T> obtenerElemento(T n) {
//        if (!esVacia()) {
//            Nodo<T> aux = inicio;
//            while (aux != null && !aux.dato.equals(n)) {
//                aux = aux.siguiente;
//            }
//            return aux;
//        } else {
//            return null;
//        }
//
//    }
//
//    @Override
//    public Nodo<T> obtenerElementoPorAtributo(T n) {
//        return obtenerElemento(n); // Asumo que la comparación es por el objeto completo
//    }
//
//    @Override
//    public boolean existeElemento(T n) {
//        return obtenerElemento(n) != null;
//    }

    @Override
    public void eliminarElemento(T n) {
        borrarElemento(n); // Reutilizo la lógica de borrarElemento
    }

    @Override
    public void agregarOrdenado(T n) {
        if (n instanceof Comparable) {
            agregarOrdenado(n, (Comparator<T>) Comparator.naturalOrder());
        } else {
            throw new ClassCastException("El objeto no implementa Comparable y no se proporcionó un Comparator.");
        }
    }

    @Override
    public void agregarOrdenado(T valor, Comparator<T> comparator) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);

        if (esVacia() || comparator.compare(valor, inicio.dato) < 0) {
            nuevoNodo.siguiente = inicio;
            inicio = nuevoNodo;
            if (fin == null) fin = nuevoNodo;
        } else {
            Nodo<T> actual = inicio;
            while (actual.siguiente != null && comparator.compare(valor, actual.siguiente.dato) >= 0) {
                actual = actual.siguiente;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
            if (nuevoNodo.siguiente == null) fin = nuevoNodo;
        }
        cantElementos++;
    }

    @Override
    public boolean estaOrdenada() {
        if (esVacia() || inicio.siguiente == null) {
            return true;
        }
        Nodo<T> actual = inicio;
        while (actual.siguiente != null) {
            if (actual.dato instanceof Comparable) {
                if (((Comparable<T>) actual.dato).compareTo(actual.siguiente.dato) > 0) {
                    return false;
                }
            } else {
                throw new ClassCastException("Los objetos en la lista no implementan Comparable.");
            }
            actual = actual.siguiente;
        }
        return true;
    }

    @Override
    public int obtenerPosDeElementoPorAtributo(T x) {
        Nodo<T> actual = inicio;
        int posicion = 0;
        while (actual != null) {
            if (actual.dato.equals(x)) {
                return posicion;
            }
            actual = actual.siguiente;
            posicion++;
        }
        return -1; // No encontrado
    }

    @Override
    public ListaSimple<T> invertir() {
        ListaSimple<T> retorno = new ListaSimple<>();
        Nodo<T> actual = inicio;
        while (actual != null) {
            retorno.agregarInicio(actual.dato);
            actual = actual.siguiente;
        }
        return retorno;
    }
}
