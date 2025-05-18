
package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Test03BuscarViajero {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void buscarViajeroOk() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        retorno = s.buscarViajeroPorCedula("1.914.689-5");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(1, retorno.getValorInteger());

        assertEquals("1.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar", retorno.getValorString());


    }



    @Test
    void buscarViajero_2Comparaciones_IzquierdaOk() {
        s.registrarViajero("2.000.000-0", "Carlos", "carlos@ort.edu.uy", 40, Categoria.PLATINO);
        s.registrarViajero("1.000.000-0", "Ana", "ana@ort.edu.uy", 30, Categoria.ESTANDAR);

        Retorno retorno = s.buscarViajeroPorCedula("1.000.000-0");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, retorno.getValorInteger());
        assertEquals("1.000.000-0;Ana;ana@ort.edu.uy;30;Estándar", retorno.getValorString());
    }

    @Test
    void buscarViajero_3Comparaciones_DerechaIzquierdaOk() {
        s.registrarViajero("2.000.000-0", "Carlos", "carlos@ort.edu.uy", 40, Categoria.PLATINO);
        s.registrarViajero("3.000.000-0", "Beto", "beto@ort.edu.uy", 25, Categoria.ESTANDAR);
        s.registrarViajero("2.500.000-0", "Dana", "dana@ort.edu.uy", 28, Categoria.FRECUENTE);

        Retorno retorno = s.buscarViajeroPorCedula("2.500.000-0");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(3, retorno.getValorInteger());
        assertEquals("2.500.000-0;Dana;dana@ort.edu.uy;28;Frecuente", retorno.getValorString());
    }

    //----------------------------------------------------------------


   /* 1. Si la cédula es vacía o null.


*/
    @Test
    void buscarViajeroCedulaVacioError1() {
        // No es necesario registrar un viajero para este test
        Retorno retorno = s.buscarViajeroPorCedula("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

         retorno = s.buscarViajeroPorCedula(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    //-------------------------------------------------------------

    //   2. Si la cédula no tiene formato válido.
    @Test
    void buscarViajeroCedulaFormatoInvalidoError2() {
        // No es necesario registrar un viajero para este test
        Retorno retorno = s.buscarViajeroPorCedula("4.5698-3");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("42365987");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

    }

    //3. Si no existe un viajero registrado con esa cédula.
    @Test
    void buscarViajeroNoRegistradoError3() {
        // Cedula correcta pero no registrada
        Retorno retorno = s.buscarViajeroPorCedula("2.000.000-0");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("3.000.000-0");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }






}
