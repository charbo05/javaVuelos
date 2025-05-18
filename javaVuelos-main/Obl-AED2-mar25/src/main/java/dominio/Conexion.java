package dominio;

import tadsAux.ListaImpl;

//Arista
public class Conexion {

    private Ciudad origen;
    private Ciudad destino;

    public void setVuelos(ListaImpl<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    private ListaImpl<Vuelo> vuelos;
    private boolean existe;

    public Conexion(Ciudad origen, Ciudad destino) {
        this.origen = origen;
        this.destino = destino;
        this.vuelos = new ListaImpl<>();
    }

    public Conexion() {

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

        for (int i = 0; i < vuelos.cantNodos(); i++) {
            if (vuelos.obtener(i).getCodigoDeVuelo().equals(codigoVuelo)) {
                return true;
            }
        }
        return false;
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

}
