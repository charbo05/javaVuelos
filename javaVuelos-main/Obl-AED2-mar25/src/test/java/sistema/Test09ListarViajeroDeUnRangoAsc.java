package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test09ListarViajeroDeUnRangoAsc {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarViajeroCategoriaOkListaVacia() {
        s.registrarViajero("3.914.689-5", "Guillermo", "guille@ort.edu.uy", 8, Categoria.ESTANDAR);
        s.registrarViajero("2.914.689-5", "Hugo", "hugo@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("5.914.689-5", "Guillermo", "asma@ort.edu.uy", 35, Categoria.ESTANDAR);

        retorno = s.listarViajerosDeUnRangoAscendente(10);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // assertEquals(1, retorno.getValorInteger());

        assertEquals("", retorno.getValorString());

        //  System.out.println(retorno);


    }

    @Test
    void listarViajeroCategoriaOk() {
        s.registrarViajero("3.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("2.914.689-5", "Hugo", "hugo@ort.edu.uy", 8, Categoria.ESTANDAR);
        s.registrarViajero("5.914.689-5", "Guillermo", "asma@ort.edu.uy", 35, Categoria.ESTANDAR);

        retorno = s.listarViajerosDeUnRangoAscendente(3);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());



        assertEquals("3.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar|5.914.689-5;Guillermo;asma@ort.edu.uy;35;Estándar", retorno.getValorString());

        //  System.out.println(retorno);


    }


    @Test
    void listarViajeroCategoriaInvalidoMenor() {


        retorno = s.listarViajerosDeUnRangoAscendente(-1);

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());



        assertEquals("El rango debe estar entre 0 y 13", retorno.getValorString());

        //  System.out.println(retorno);


    }


    @Test
    void listarViajeroCategoriaInvalidoMayor() {


        retorno = s.listarViajerosDeUnRangoAscendente(195);

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());



        assertEquals("El rango debe estar entre 0 y 13", retorno.getValorString());

        //  System.out.println(retorno);


    }

}
