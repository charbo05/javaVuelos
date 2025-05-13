package sistema;

import dominio.Vuelo;
import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoVuelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test13ActualizarVuelo {

    Sistema s;

    @BeforeEach
    public void setUp() {
        s = new ImplementacionSistema();
        s.inicializarSistema(10);
    }



    @Test
    public void testActualizarVueloParametrosDoubleInvalidos() {
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("NYC", "New York");
        s.registrarVuelo("MVD", "NYC", "AA123", 50, 120, 500, TipoVuelo.COMERCIAL);

        Retorno r;

        r = s.actualizarVuelo("MVD", "NYC", "AA123", -10, 100, 300, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, r.getResultado());

        r = s.actualizarVuelo("MVD", "NYC", "AA123", 50, 0, 300, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, r.getResultado());

        r = s.actualizarVuelo("MVD", "NYC", "AA123", 50, 120, -5, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, r.getResultado());
    }

    @Test
    public void testActualizarVueloParametrosStringInvalidos() {
        Retorno r;

        r = s.actualizarVuelo(null, "NYC", "AA123", 50, 120, 300, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());

        r = s.actualizarVuelo("", "NYC", "AA123", 50, 120, 300, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());

        r = s.actualizarVuelo("MVD", null, "AA123", 50, 120, 300, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());

        r = s.actualizarVuelo("MVD", "", "AA123", 50, 120, 300, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());

        r = s.actualizarVuelo("MVD", "NYC", null, 50, 120, 300, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());

        r = s.actualizarVuelo("MVD", "NYC", "", 50, 120, 300, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());
    }

    @Test
    public void testActualizarVueloCiudadOrigenNoExiste() {
        s.registrarCiudad("NYC", "New York");

        Retorno r = s.actualizarVuelo("MVD", "NYC", "AA123", 50, 120, 300, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_3, r.getResultado());
    }

    @Test
    public void testActualizarVueloCiudadDestinoNoExiste() {
        s.registrarCiudad("MVD", "Montevideo");

        Retorno r = s.actualizarVuelo("MVD", "NYC", "AA123", 50, 120, 300, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_4, r.getResultado());
    }

    @Test
    public void testActualizarVueloConexionNoExiste() {
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("NYC", "New York");

        Retorno r = s.actualizarVuelo("MVD", "NYC", "AA123", 50, 120, 300, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_5, r.getResultado());

    }

    @Test
    public void testActualizarVueloNoExisteVueloConCodigo() {
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("NYC", "New York");
        s.registrarConexion("MVD", "NYC");
        s.registrarVuelo("MVD", "NYC", "BB456", 50, 120, 500, TipoVuelo.COMERCIAL);

        Retorno r = s.actualizarVuelo("MVD", "NYC", "AA123", 60, 150, 600, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_6, r.getResultado());
    }

    @Test
    public void testActualizarVueloCorrectamente() {
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("NYC", "New York");
        s.registrarConexion("MVD", "NYC");
        s.registrarVuelo("MVD", "NYC", "AA123", 50, 120, 500, TipoVuelo.COMERCIAL);

        Retorno r = s.actualizarVuelo("MVD", "NYC", "AA123", 60, 150, 600, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, r.getResultado());


    }

}
