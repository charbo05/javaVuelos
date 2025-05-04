package dominio;

import tadsAux.ListaImpl;

import java.util.Comparator;

public class Ciudad implements Comparable<Ciudad>  {

    private String codigo;
    private String nombre;


    private ListaImpl<Ciudad> conexiones = new ListaImpl<>();
    private ListaImpl<Vuelo> vuelos;


    public Ciudad(String codigo , String nombre ) {

        this.codigo = codigo;
        this.nombre = nombre;

    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ListaImpl<Ciudad> getConexiones() {
        return conexiones;
    }

    public ListaImpl<Vuelo> getVuelos() {
        return vuelos;
    }


    @Override
    public String toString() {
        return "Ciudad{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return codigo.equals(ciudad.codigo); // asumimos que el código es único
    }

    @Override
    public int compareTo(Ciudad otra) {
        return this.codigo.compareTo(otra.codigo);
    }


    public static class ComparadorPorCodigo implements Comparator<Ciudad> {
        @Override
        public int compare(Ciudad c1, Ciudad c2) {
            return c1.getCodigo().compareTo(c2.getCodigo());
        }
    }

    public boolean buscarConexion(Ciudad ciudadA, Ciudad ciudadB) {
        if (ciudadA == null || ciudadB == null) {
            return false;
        }

        return ciudadA.getConexiones().contiene(ciudadB);
    }

}
