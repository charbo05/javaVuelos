package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test05ListarViajeroPorCedulaAscendente {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }


    //Controlo que si la lista esta vacia que no  te explote
    @Test
    void listarViajeroVaciaOk() {

        retorno = s.listarViajerosPorCedulaAscendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // assertEquals(1, retorno.getValorInteger());

        assertEquals("", retorno.getValorString());



    }
    @Test
    void listarViajeroOk() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("2.914.689-5", "Guillermo", "guille2@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("3.914.689-5", "Guillermo", "guille3@ort.edu.uy", 35, Categoria.ESTANDAR);

        retorno = s.listarViajerosPorCedulaAscendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

       // assertEquals(1, retorno.getValorInteger());

        assertEquals("1.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar|2.914.689-5;Guillermo;guille2@ort.edu.uy;35;Estándar|3.914.689-5;Guillermo;guille3@ort.edu.uy;35;Estándar", retorno.getValorString());



    }
/*1.914.689-5
* 2.914.689-5*/
    @Test
    void listarViajeroDesordenadosOk() {
        s.registrarViajero("2.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("3.914.689-5", "Guillermo", "guille2@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.914.689-5", "Guillermo", "guille3@ort.edu.uy", 35, Categoria.ESTANDAR);

        retorno = s.listarViajerosPorCedulaAscendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());



        assertEquals("1.914.689-5;Guillermo;guille3@ort.edu.uy;35;Estándar|2.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar|3.914.689-5;Guillermo;guille2@ort.edu.uy;35;Estándar", retorno.getValorString());



    }
}