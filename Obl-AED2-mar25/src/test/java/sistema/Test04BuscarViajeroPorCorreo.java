package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test04BuscarViajeroPorCorreo {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void buscarViajeroOk() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        retorno = s.buscarViajeroPorCorreo("guille@ort.edu.uy");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(1, retorno.getValorInteger());

        assertEquals("1.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar", retorno.getValorString());


    }

    @Test
    void buscarViajero_2Comparaciones_IzquierdaOk() {
        s.registrarViajero("2.000.000-0", "Carlos", "carlos@ort.edu.uy", 40, Categoria.PLATINO);
        s.registrarViajero("1.000.000-0", "Ana", "ana@ort.edu.uy", 30, Categoria.ESTANDAR);

        Retorno retorno = s.buscarViajeroPorCorreo("ana@ort.edu.uy");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, retorno.getValorInteger());
        assertEquals("1.000.000-0;Ana;ana@ort.edu.uy;30;Estándar", retorno.getValorString());
    }

    @Test
    void buscarViajero_3Comparaciones_DerechaIzquierdaOk() {
        s.registrarViajero("2.000.000-0", "Carlos", "carlos@ort.edu.uy", 40, Categoria.PLATINO);
        s.registrarViajero("3.000.000-0", "Beto", "beto@ort.edu.uy", 25, Categoria.ESTANDAR);
        s.registrarViajero("2.500.000-0", "Dana", "dana@ort.edu.uy", 28, Categoria.FRECUENTE);


        Retorno retorno = s.buscarViajeroPorCorreo("dana@ort.edu.uy");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(3, retorno.getValorInteger());
        assertEquals("2.500.000-0;Dana;dana@ort.edu.uy;28;Frecuente", retorno.getValorString());
    }

    //----------------------------------------------------------------


    @Test
    void buscarViajeroCedulaVaciaError1() {
        // No es necesario registrar un viajero para este test
        Retorno retorno = s.buscarViajeroPorCedula("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    //-------------------------------------------------------------

    @Test
    void buscarViajeroCedulaFormatoInvalidoError2() {
        // No es necesario registrar un viajero para este test
        Retorno retorno = s.buscarViajeroPorCedula("12545698");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("12325-1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

    }

    @Test
    void buscarViajeroNoRegistradoError3() {
        // Cedula correcta pero no registrada
        Retorno retorno = s.buscarViajeroPorCedula("1.236.365-8");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.buscarViajeroPorCedula("1.234.567-1");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }






}
