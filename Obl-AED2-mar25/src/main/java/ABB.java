
public class ABB {
    private NodoABB raiz;



//INNERCLASS
    private class NodoABB {
        private int dato;
        private NodoABB izq;
        private NodoABB der;

        public NodoABB(int dato) {
            this.dato = dato;
        }

        @Override
        public String toString() {
            return "NodoABB{" + dato + '}';
        }
    }



    public void insertar(int dato) {
        if (raiz == null) {
            raiz = new NodoABB(dato);
        } else {
            insertar(raiz, dato);
        }
    }

    private void insertar(NodoABB nodo, int dato) {
        if (dato < nodo.dato) {
            if (nodo.izq == null) {
                nodo.izq = new NodoABB(dato);
            } else {
                insertar(nodo.izq, dato);
            }
        } else {
            if (nodo.der == null) {
                nodo.der = new NodoABB(dato);
            } else {
                insertar(nodo.der, dato);
            }
        }
    }


    public int altura() {

        return altura(raiz);
    }

    private int altura(NodoABB nodo) {
        if (nodo == null) {
            return -1;
        }
        if (nodo.izq == null && nodo.der == null) {
            return 0;
        }
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }


    public boolean equilibrado() {
        return equilibrado(raiz);
    }

    private boolean equilibrado(NodoABB nodo) {
        if (nodo != null) {
            return Math.abs(altura(nodo.izq) - altura(nodo.der)) <= 1 &&
                    equilibrado(nodo.izq) &&
                    equilibrado(nodo.der);
        } else {
            return true;
        }
    }




    //Pos: Inserta el dato pasado como parámetro en el árbol manteniéndolo ordenado.
    public void insertar2(int x) {

        if (raiz == null) {
            raiz = new NodoABB(x);
        } else {
            insertarRec(raiz, x);
        }
    }

    private NodoABB insertarRec(NodoABB actual, int valor) {

        if (actual == null) {
            return new NodoABB(valor);
        }

        //si el valor a insertar es menor al valor del nodo actual, voy por la izquierda
        if (valor < actual.dato) {
            actual.izq = insertarRec(actual.izq, valor);
        }
        //si el valor a insertr es mayor vamos por la derecha
        else if (valor > actual.dato) {

            actual.der = insertarRec(actual.der, valor);
        }

        return actual;
    }


    // Función para realizar el recorrido inorden e imprimir los nodos
    public void listarAscendente() {
        imprimirInordenRecursivo(this.raiz);
        System.out.println(); // Agregar una nueva línea al final
    }

    private void imprimirInordenRecursivo(NodoABB nodoActual) {
        if (nodoActual != null) {
            // 1. Recorrer el subárbol izquierdo
            imprimirInordenRecursivo(nodoActual.izq);
            // 2. Imprimir el valor del nodo actual
            System.out.print(nodoActual.dato + " ");
            // 3. Recorrer el subárbol derecho
            imprimirInordenRecursivo(nodoActual.der);
        }
    }

    /*
    Pos: Retorna true si y solo si el dato pasado como parámetro pertenece al ABB.  */

    public boolean pertenece(int x) {

        return perteneceRec(raiz, x);
    }

    private boolean perteneceRec(NodoABB actual, int valor) {

        if (actual == null) {
            return false;
        }

        //si encuentro el valor devulvo true
        if (actual.dato == valor) {
            return true;
        }
        //si el valo buscado es mas pequeño qe el valor del nodo,
        // tomo el subarbol izquierdo
        else if (valor < actual.dato) {
            return perteneceRec(actual.izq, valor);
        } else {
            return perteneceRec(actual.der, valor);
        }


    }


    // Función para realizar el recorrido inorden e imprimir los nodos
    public void ListarDesc() {
        imprimirDescRecursivo(this.raiz);
        System.out.println(); // Agregar una nueva línea al final
    }

    private void imprimirDescRecursivo(NodoABB nodoActual) {
        if (nodoActual != null) {
            // 1. Recorrer el subárbol derecho
            imprimirDescRecursivo(nodoActual.der);
            // 2. Imprimir el valor del nodo actual
            System.out.print(nodoActual.dato + " ");
            // 3. Recorrer el subárbol derecho
            imprimirDescRecursivo(nodoActual.izq);
        }
    }

    //Pos: Elimina el menor elemento del ABB y lo retorna.
     public int borrarMinimo() {
        //la raiz es el mas chico
        if(raiz.izq == null){
            int datoAborrar = raiz.dato;
            raiz = raiz.der;
            return datoAborrar;
        }

        else{

            return borrarMinimo(raiz);
        }

     }

     private int borrarMinimo (NodoABB nodo){

        if(nodo.izq.izq==null){

            int datoAborrar = nodo.izq.dato;
            nodo.izq = nodo.izq.der;
            return datoAborrar;
        }else{
            return borrarMinimo(nodo.izq);
        }
     }




}


