package tadsAux;

import interfaz.ICola;

public class Cola<T extends Comparable<T>> implements ICola<T> 
{
    private Nodo inicio;
    private Nodo fin;
    private int cantElementos;

    public Cola() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }

    
    public boolean esVacia() {
        return inicio == null ;
    }

    //innerClass

     private class Nodo {
        private T dato;
        private Nodo izq;
        private Nodo der;

        public Nodo(T dato) {
            this.dato = dato;
        }

        public T getDato() {
            return dato;
        }

        public Nodo getIzq() {
            return izq;
        }

        public Nodo getDer() {
            return der;
        }

        @Override
        public String toString() {
            return "Nodo{" + dato + '}';
        }
    }


    @Override
    public void encolar(T n) {
        Nodo nuevo = new Nodo(n);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        cantElementos++;
    }

    @Override
    public void desencolar() {
        if (!esVacia()) {
            if (inicio.getSiguiente() == null) {
                inicio = null;
                fin = null;
            } else {
                NodoSE aBorrar = inicio;
                inicio = inicio.getSiguiente();
                aBorrar.setSiguiente(null);
            }
            cantElementos--;
        }
    }
//
//    @Override
//    public NodoSE obtenerPrimerElemento() {
//        if (!esVacia()) {
//            return inicio;
//        } else {
//            return null;
//        }
//    }
//    
   



    @Override
    public void vaciar() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }

    @Override
    public void mostrar() {
        if (!esVacia()) {
            NodoSE aux = inicio;
            while (aux != null) {
                System.out.print(aux.getDato() + " ");
                aux = aux.getSiguiente();
            }
            System.out.println();
        } else {
            System.out.println("Lista vacía.");
        }

    }

    @Override
    public int cantElementos() {
        return cantElementos;
    }

    @Override
    public boolean existeElemento(T x) {
        boolean existe = false;
        NodoSE aux = inicio;
        while (aux != null && !existe) {
            if (aux.getDato().equals(x)) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }
        return existe;
    }

    @Override
    public boolean EsVacia() {
        return inicio == null;
    }
   
}


