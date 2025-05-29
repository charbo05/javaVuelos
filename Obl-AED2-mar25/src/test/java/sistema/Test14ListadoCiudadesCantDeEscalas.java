package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoVuelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test14ListadoCiudadesCantDeEscalas {
    private Retorno r;
    private  Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {

        s = new ImplementacionSistema();
        s.inicializarSistema(50);


        // Agregar ciudades al sistema
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("STO", "Santiago");
        s.registrarCiudad("BUE", "Buenos Aires");
        s.registrarCiudad("LIM", "Lima");

        // Agregar conexiones
        s.registrarConexion("MVD", "BUE");
        s.registrarConexion("BUE", "STO");
        s.registrarConexion("BUE", "LIM");
        s.registrarConexion("STO", "LIM");
        s.registrarConexion("MVD", "LIM");
        s.registrarConexion("MVD", "STO");

        s.registrarConexion("LIM", "MVD");


        s.registrarVuelo("MVD", "STO", "vuelo3", 1000, 300, 500, TipoVuelo.COMERCIAL);
        s.registrarVuelo("MVD", "BUE", "vuelo3", 1000, 300, 500, TipoVuelo.COMERCIAL);
        s.registrarVuelo("BUE", "LIM", "vuelo1", 1000, 200, 500, TipoVuelo.COMERCIAL);
        s.registrarVuelo("LIM", "MVD", "vuelo2", 1000, 300, 500, TipoVuelo.COMERCIAL);
        s.registrarVuelo("MVD", "STO", "vuelo3", 1000, 300, 500, TipoVuelo.COMERCIAL);
    }

    @Test
    public void testCantidadNegativa() {
        Retorno r = s.listadoCiudadesCantDeEscalas("MVD", -1);
        assertEquals(Retorno.Resultado.ERROR_1, r.getResultado());
    }

    @Test
    public void testCodigoVacioONull() {
        assertEquals(Retorno.Resultado.ERROR_2, s.listadoCiudadesCantDeEscalas("", 2).getResultado());
        assertEquals(Retorno.Resultado.ERROR_2, s.listadoCiudadesCantDeEscalas(null, 2).getResultado());
    }

    @Test
    public void testCiudadNoRegistrada() {
        Retorno r = s.listadoCiudadesCantDeEscalas("XXX", 2);
        assertEquals(Retorno.Resultado.ERROR_3, r.getResultado());
    }

    @Test
    public void testConCeroEscalas() {
        Retorno r = s.listadoCiudadesCantDeEscalas("MVD", 0);
        assertEquals(Retorno.Resultado.OK, r.getResultado());
        assertEquals("MVD;Montevideo", r.getValorString());
    }

    @Test
    public void testConUnaEscala() {
        Retorno r = s.listadoCiudadesCantDeEscalas("LIM", 1);
        assertEquals(Retorno.Resultado.OK, r.getResultado());
        assertEquals("LIM;Lima|MVD;Montevideo", r.getValorString());
    }



    @Test
    public void testConTresEscalas() {
        Retorno r = s.listadoCiudadesCantDeEscalas("MVD", 3);
        assertEquals(Retorno.Resultado.OK, r.getResultado());
        assertEquals("BUE;Buenos Aires|LIM;Lima|MVD;Montevideo|STO;Santiago", r.getValorString());
    }

    @Test
    public void testOrdenLexicografico() {
        s.registrarCiudad("AAA", "Ciudad AAA");
        s.registrarCiudad("ZZZ", "Ciudad ZZZ");
        s.registrarConexion("ZZZ", "AAA");
        s.registrarVuelo("ZZZ", "AAA", "vuelo3", 1000, 300, 500, TipoVuelo.COMERCIAL);
        Retorno r = s.listadoCiudadesCantDeEscalas("ZZZ", 1);
        assertEquals(Retorno.Resultado.OK, r.getResultado());
        assertEquals("AAA;Ciudad AAA|ZZZ;Ciudad ZZZ", r.getValorString());
    }

    }



