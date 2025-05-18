package sistema;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10RegistrarCiudad {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void RegistrarCiudadOk() {
       retorno = s.registrarCiudad("123456789", "Ciudad de prueba");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());


    }

    @Test
    void RegistrarCiudadOkMasdeUna() {
        retorno = s.registrarCiudad("123456789", "Ciudad de prueba");
        retorno = s.registrarCiudad("16789", "Ciudad de prueba2");
        retorno = s.registrarCiudad("12346789", "Ciudad de prueba3");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    }

    @Test
    void RegistrarCiudadOkMasdelMaximoError1() {
        retorno = s.registrarCiudad("123456789", "Ciudad de prueba");
        retorno = s.registrarCiudad("16789", "Ciudad de prueba2");
        retorno = s.registrarCiudad("12346789", "Ciudad de prueba3");
        retorno = s.registrarCiudad("19", "Ciudad de prueba4");
        retorno = s.registrarCiudad("169", "Ciudad de prueba5");
        retorno = s.registrarCiudad("136789", "Ciudad de prueba6");
        retorno = s.registrarCiudad("12345679", "Ciudad de prueba7");
        retorno = s.registrarCiudad("162", "Ciudad de prueba8");
        retorno = s.registrarCiudad("1239", "Ciudad de prueba9");
       retorno = s.registrarCiudad("197", "Ciudad de prueba0");
        retorno = s.registrarCiudad("16", "Ciudad de prueba12");
        retorno = s.registrarCiudad("1", "Ciudad de prueba13");

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    @Test
    void RegistrarCiudadCodigoONombreVacioNullError2() {
        retorno = s.registrarCiudad("", "Ciudad de prueba");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCiudad("12", null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());


    }

    @Test
    void RegistrarCiudadExistenteError3() {
        retorno = s.registrarCiudad("123", "Ciudad de prueba");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("123", "Ciudad de prueba");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());


    }


}
