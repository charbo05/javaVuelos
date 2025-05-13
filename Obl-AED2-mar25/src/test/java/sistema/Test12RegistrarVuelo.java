package sistema;


import interfaz.Retorno;
import interfaz.TipoVuelo;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test12RegistrarVuelo {

    Sistema s;

    @BeforeEach
    public void setUp() {
        s = new ImplementacionSistema();
        s.inicializarSistema(10);
    }

    @Test
    public void testRegistrarVueloParametrosDoubleInvalidos() {
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("NYC", "New York");

        Retorno r = s.registrarVuelo("MVD", "NYC", "AA123", -1, 120, 500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, r.getResultado());

        r = s.registrarVuelo("MVD", "NYC", "AA123", 50, 0, 500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, r.getResultado());

        r = s.registrarVuelo("MVD", "NYC", "AA123", 50, 120, -100, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_1, r.getResultado());
    }

    @Test
    public void testRegistrarVueloParametrosStringInvalidos() {
        Retorno r = s.registrarVuelo(null, "NYC", "AA123", 50, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());

        r = s.registrarVuelo("", "NYC", "AA123", 50, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());

        r = s.registrarVuelo("MVD", null, "AA123", 50, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());

        r = s.registrarVuelo("MVD", "", "AA123", 50, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());

        r = s.registrarVuelo("MVD", "NYC", null, 50, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());

        r = s.registrarVuelo("MVD", "NYC", "", 50, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());
    }

    @Test
    public void testRegistrarVueloCiudadOrigenNoExiste() {
        s.registrarCiudad("NYC", "New York");


        Retorno r = s.registrarVuelo("MVD", "NYC", "AA123", 50, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_3, r.getResultado());

    }

    @Test
    public void testRegistrarVueloCiudadDestinoNoExiste() {
        s.registrarCiudad("MVD", "Montevideo");

        Retorno r = s.registrarVuelo("MVD", "NYC", "AA123", 50, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_4, r.getResultado());
    }

    @Test
    public void testRegistrarVueloNoExisteConexion() {
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("NYC", "New York");


        Retorno r = s.registrarVuelo("MVD", "NYC", "AA123", 50, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_5, r.getResultado());
    }

    @Test
    public void testRegistrarVueloCodigoRepetidoEnMismaConexion() {
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("NYC", "New York");
        s.registrarConexion("MVD", "NYC");

        // Asegúrate de que no hay conexión previa, para que el código pase esa validación
        Retorno r1 = s.registrarVuelo("MVD", "NYC", "AA123", 50, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, r1.getResultado());

        // Intentar registrar otro vuelo con el mismo código en la misma conexión
        Retorno r2 = s.registrarVuelo("MVD", "NYC", "AA123", 60, 130, 600, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.ERROR_6, r2.getResultado());
    }

    @Test
    public void testRegistrarVueloCorrectamente() {
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("NYC", "New York");
        s.registrarConexion("MVD", "NYC");//Registro la conexion para poder registrar luego el vuelo

        Retorno r = s.registrarVuelo("MVD", "NYC", "AA123", 50, 120, 500, TipoVuelo.PRIVADO);
        assertEquals(Retorno.Resultado.OK, r.getResultado());
    }
}

