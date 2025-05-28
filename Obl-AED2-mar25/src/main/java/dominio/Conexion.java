package dominio;

import tadsAux.ListaImpl;

//Arista
public class Conexion {

    private Ciudad origen;
    private Ciudad destino;

//    public void setVuelos(ListaImpl<Vuelo> vuelos) {
//        this.vuelos = vuelos;
//    }

    private ListaImpl<Vuelo> vuelos;
    private boolean existe;

    public Conexion(Ciudad origen, Ciudad destino) {
        this.origen = origen;
        this.destino = destino;
        this.vuelos = new ListaImpl<>();
    }


    public Conexion() {
        this.vuelos = new ListaImpl<>();
    }

    public ListaImpl<Vuelo> getVuelos() {
        return vuelos;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public Ciudad getDestino() {
        return destino;
    }


    public boolean ExisteVuelo(String codigoVuelo) {
        // Acá asumimos que vuelos ya no es null
        return vuelos != null && vuelos.contieneElemento(new Vuelo(codigoVuelo, 0, 0, 0, null));
    }


    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
        if (!existe) {
            this.origen = null;
            this.destino = null;
            this.vuelos = null;

        }
    }

    public void agregarVueloEnConexion(String codigoVuelo) {

        if (this.ExisteVuelo(codigoVuelo)) {
            System.out.println("Ya existe el vuelo con el codigo: " + codigoVuelo);
            ;
        } else {
            Vuelo v = new Vuelo(codigoVuelo, 0, 0, 0, null);

            vuelos.insertar(v);
        }

    }

    public Vuelo obtenerVuelo(String codigoVuelo){
        for(int i=0;i<vuelos.cantNodos();i++){
            if(vuelos.obtener(i).getCodigoDeVuelo().equals(codigoVuelo)){
                return vuelos.obtener(i);
            }
        }
        return null;
    }
}

