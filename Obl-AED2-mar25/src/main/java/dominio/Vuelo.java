package dominio;

import interfaz.TipoVuelo;

public class Vuelo implements Comparable<Vuelo> {
    private String codigoDeVuelo;

    private double combustible;
    private double minutos;
    private double costoEnDolares;
    private TipoVuelo tipoDeVuelo;

    public Vuelo(String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        this.codigoDeVuelo = codigoDeVuelo;

        this.combustible = combustible;
        this.minutos = minutos;
        this.costoEnDolares = costoEnDolares;
        this.tipoDeVuelo = tipoDeVuelo;
    }

    public String getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public void setCodigoDeVuelo(String codigoDeVuelo) {
        this.codigoDeVuelo = codigoDeVuelo;
    }



    public double getCombustible() {
        return combustible;
    }

    public void setCombustible(double combustible) {
        this.combustible = combustible;
    }

    public double getMinutos() {
        return minutos;
    }

    public void setMinutos(double minutos) {
        this.minutos = minutos;
    }

    public double getCostoEnDolares() {
        return costoEnDolares;
    }

    public void setCostoEnDolares(double costoEnDolares) {
        this.costoEnDolares = costoEnDolares;
    }

    public TipoVuelo getTipoDeVuelo() {
        return tipoDeVuelo;
    }

    public void setTipoDeVuelo(TipoVuelo tipoDeVuelo) {
        this.tipoDeVuelo = tipoDeVuelo;
    }


    //despues hay que analizar si debemos agregar un metodo mas de comparacion
    @Override
    public int compareTo(Vuelo o) {
        return 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return codigoDeVuelo.equals(vuelo.codigoDeVuelo); // asumimos que el código es único
    }


}