package dominio;

import java.util.Comparator;

public class Ciudad implements Comparable<Ciudad> {

    private String codigo;
    private String nombre;


    public Ciudad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;

    }

    // Getters y Setters
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


//
//    public Vuelo obtenerVuelo(String codigoVuelo) {
//        for (int i = 0; i < vuelosSalientes.cantNodos(); i++) {
//            Vuelo v = vuelosSalientes.obtener(i);
//            if (v.getCodigoDeVuelo().equals(codigoVuelo)) {
//                return v;
//            }
//        }
//        return null;
//    }



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
        return codigo.equals(ciudad.codigo);
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
}
