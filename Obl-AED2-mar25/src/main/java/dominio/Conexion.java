// Cada conexión se representa como un par (ciudadOrigen -> ciudadDestino) con vuelos
public class Conexion
 {
    private Ciudad origen;
    private Ciudad destino;
    private List<Vuelo> vuelos;

    public Conexion(Ciudad origen, Ciudad destino) {
        this.origen = origen;
        this.destino = destino;
        this.vuelos = new ArrayList<>();
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public boolean existeVuelo(String codigoVuelo) {
        return vuelos.stream().anyMatch(v -> v.getCodigo().equals(codigoVuelo));
    }
}
