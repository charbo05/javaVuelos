package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test11RegistrarConexion {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    public void testRegistrarConexionParametrosInvalidos() {
        Retorno r = s.registrarConexion(null, "MVD");
        assertEquals(Retorno.Resultado.ERROR_1, r.getResultado());

        r = s.registrarConexion("", "MVD");
        assertEquals(Retorno.Resultado.ERROR_1, r.getResultado());

        r = s.registrarConexion("MVD", null);
        assertEquals(Retorno.Resultado.ERROR_1, r.getResultado());

        r = s.registrarConexion("MVD", "");
        assertEquals(Retorno.Resultado.ERROR_1, r.getResultado());
    }

    @Test
    public void testRegistrarConexionCiudadOrigenNoExiste() {
        s.registrarCiudad("MVD", "Montevideo");
        Retorno r = s.registrarConexion("NYC", "MVD");
        assertEquals(Retorno.Resultado.ERROR_2, r.getResultado());
    }


    @Test
    public void testRegistrarConexionCiudadDestinoNoExiste() {
        s.registrarCiudad("MVD", "Montevideo");
        Retorno r = s.registrarConexion("MVD", "NYC");
        assertEquals(Retorno.Resultado.ERROR_3, r.getResultado());
    }

    @Test
    public void testRegistrarConexionYaExiste() {
        s.registrarCiudad("MVD", "Montevideo");
        s.registrarCiudad("NYC", "New York");
        s.registrarConexion("MVD", "NYC");

        Retorno r = s.registrarConexion("MVD", "NYC");
        assertEquals(Retorno.Resultado.ERROR_4, r.getResultado());
    }

    @Test
    public void testRegistrarConexionOk() {
        s.registrarCiudad("BSS", "Barcelona");
        s.registrarCiudad("MCJ", "Manchester");

      Retorno r =  s.registrarConexion("BSS", "MCJ");

        assertEquals(Retorno.Resultado.OK, r.getResultado());
    }



}
