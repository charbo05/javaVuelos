package tadsAux;

import dominio.Ciudad;
import dominio.Conexion;

// Clase Grafo (lista de ciudades)
public class GrafoCiudades {

    private Conexion[][] conexiones;
    private Ciudad[] ciudades;
    private int cantidadCiudades;
    private final int maxCiudades;
    private final boolean dirigido;

    public GrafoCiudades(int cantMaxCiudades, boolean dirigido) {

        this.cantidadCiudades = 0;
        conexiones = new Conexion[cantMaxCiudades][cantMaxCiudades];

        if (dirigido) {
            for (int i = 0; i < conexiones.length; i++) {
                for (int j = 0; j < conexiones.length; j++) {

                    conexiones[i][j] = new Conexion();
                }
            }

        } else {
            for (int i = 0; i < conexiones.length; i++) {
                for (int j = i; j < conexiones.length; j++) {
                    Conexion conexion = new Conexion();
                    conexiones[i][j] = conexion;
                    conexiones[j][i] = conexion;
                }
            }
        }
        ciudades = new Ciudad[cantMaxCiudades];
        this.maxCiudades = cantMaxCiudades;
        this.dirigido = dirigido;
    }

    public void agregarCiudad(Ciudad ciudad) {
        if (cantidadCiudades < maxCiudades) {
            int posLibre = obtenerPosLibre();
            if (posLibre != -1) {
                ciudades[posLibre] = ciudad;
                cantidadCiudades++;
            }
        }
    }

    public void borrarCiudad(Ciudad ciudad) {
        int pos = obtenerPos(ciudad);
        ciudades[pos] = null;
        cantidadCiudades--;

        for (int i = 0; i < conexiones.length; i++) {
            conexiones[pos][i].setExiste(false); //Adyacentes
            conexiones[i][pos].setExiste(false);//Incidentes
        }

    }

    public void agregarConexion(Ciudad origen, Ciudad destino) {

        int posInicio = obtenerPos(origen);
        int posfin = obtenerPos(destino);

        Conexion aux = conexiones[posInicio][posfin];
        if(aux!=null){
            aux.setExiste(true);

        }else{

            System.out.println("No existe la conexion");
        }
        }

        public boolean existeConexion(Ciudad origen, Ciudad destino){
        int posInicio = obtenerPos(origen);
            int posfin = obtenerPos(destino);
            return conexiones[posInicio][posfin].isExiste();
        }


        public void borrarConexion(Ciudad origen, Ciudad destino) {
        int posInicio = obtenerPos(origen);
        int posfin = obtenerPos(destino);

            if (dirigido) {
                conexiones[posInicio][posfin].setExiste(false);
            } else {
                conexiones[posInicio][posfin].setExiste(false);
                conexiones[posfin][posInicio].setExiste(false);
            }

        }

        public Conexion obtenerConexion(Ciudad origen, Ciudad destino){
            int posInicio = obtenerPos(origen);
            int posfin = obtenerPos(destino);
            return conexiones[posInicio][posfin];
        }

        public ListaImpl<Ciudad> obtenerCiudadesAdyacentes(Ciudad ciudad){
        ListaImpl<Ciudad> lista = new ListaImpl<>();
        int pos = obtenerPos(ciudad);
        for(int i=0;i<conexiones.length;i++){
            if (conexiones[pos][i] != null && conexiones[pos][i].isExiste()) {
                lista.insertar(ciudades[i]);
            }

        }
        return lista;

        }



    public Ciudad buscarCiudad(String codigoCiudad) {
       Ciudad ciudadAux = new Ciudad(codigoCiudad, "");
        int pos = obtenerPos(ciudadAux);

        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] != null && ciudades[pos].equals(ciudadAux)) {
                return ciudadAux;
            }
        }
        return null;
    }







    private int obtenerPosLibre() {
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(Ciudad ciudad) {
        for (int i = 0; i < ciudades.length; i++) {
            if (ciudades[i] != null && ciudades[i].equals(ciudad)) {
                return i;
            }
        }
        return -1;

    }
}
