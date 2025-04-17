package dominio;
import interfaz.Categoria;

import java.util.Comparator;
import java.util.Objects;

public class Viajero implements Comparable<Viajero>

{

    String cedula;
    String nombre;
    String correo;
    int edad;
    Categoria categoria;

    public Viajero(String cedula, String nombre, String correo, int edad, Categoria categoria) {

        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.categoria = categoria;
    }

    public Viajero(){}

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getEdad() {
        return edad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

//---------------------------------------------
    @Override
    public int compareTo(Viajero o)
    {
        return this.getCedula().compareTo(o.getCedula());
    }



    @Override
    //Creacion del equals para realizar comparacion mediante metodo existeElemento de ListaSimple
    public boolean equals(Object obj)
    {
        //validamos que el objeto a comparar no sea null
        if (obj == null) {
            return false;
        }
        //validamos que sean de la misma clase
        if (getClass() != obj.getClass()) {
            return false;
        }
        //convertimos el objeto a medico
        final Viajero other = (Viajero) obj;
        // verificamos si tienen igual codigo de medico
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;//si no tienen igual cedula
        } else {
            return true;//si tienen igual cedula
        }
    }
    /*   Formato de retorno del valorString: cedula;nombre;correo;edad;categoría.
    Por ejemplo, valorString del retorno en una consulta válida:
    1.914.689-5;Guillermo;guille@ort.edu.uy,35,Estándar
    */

  /*  @Override
    public String toString() {
        return "Cedula: " + cedula +
                ", Nombre: " + nombre +
                ", Correo: " + correo +
                ", Edad: " + edad +
                ", Categoria: " + categoria; // Se muestra el texto de forma legible
    }
*/
    // en Viajero.java
    @Override
    public String toString() {
        return cedula + ";" + nombre + ";" + correo + ";" + edad + ";" + categoria.toString().charAt(0) + categoria.toString().substring(1).toLowerCase();
    }



    public static Comparator<Viajero> comparadorPorCorreo = new Comparator<Viajero>() {
        @Override
        public int compare(Viajero v1, Viajero v2) {
            return v1.getCorreo().compareTo(v2.getCorreo());
        }
    };





}
