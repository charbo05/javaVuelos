package tadsAux;

import dominio.Ciudad;

// Clase Grafo (lista de ciudades)
public class GrafoCiudades {
    private ListaImpl<Ciudad> ciudades;

    public GrafoCiudades() {
        this.ciudades = new ListaImpl<>();
    }

    public void agregarCiudad(Ciudad ciudad) {
        ciudades.insertar(ciudad);
    }

    public Ciudad buscarCiudad(String codigo) {
        for (int i = 0; i < ciudades.cantNodos(); i++) {
            Ciudad c = ciudades.obtener(i);
            if (c.getCodigo().equals(codigo)) {
                return c;
            }
        }
        return null;
    }

    public boolean existeConexion(Ciudad origen, Ciudad destino) {
        return origen.tieneConexionCon(destino);
    }

    public boolean buscarConexion(String codigoOrigen, String codigoDestino) {
        Ciudad origen = buscarCiudad(codigoOrigen);
        Ciudad destino = buscarCiudad(codigoDestino);
        return (origen != null && destino != null && origen.tieneConexionCon(destino));
    }
}
