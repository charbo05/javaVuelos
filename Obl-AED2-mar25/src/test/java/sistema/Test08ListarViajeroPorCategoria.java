package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test08ListarViajeroPorCategoria {

        private Retorno retorno;
        private final Sistema s = new ImplementacionSistema();

        @BeforeEach
        public void setUp() {
            s.inicializarSistema(10);
        }

        @Test
        void listarViajeroCategoriaOk() {
            s.registrarViajero("3.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
            s.registrarViajero("2.914.689-5", "Hugo", "hugo@ort.edu.uy", 35, Categoria.ESTANDAR);
            s.registrarViajero("5.914.689-5", "Guillermo", "asma@ort.edu.uy", 35, Categoria.ESTANDAR);

            retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);

            assertEquals(Retorno.Resultado.OK, retorno.getResultado());

            // assertEquals(1, retorno.getValorInteger());

            assertEquals("2.914.689-5;Hugo;hugo@ort.edu.uy;35;Estándar|3.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar|5.914.689-5;Guillermo;asma@ort.edu.uy;35;Estándar", retorno.getValorString());

          //  System.out.println(retorno);


        }

    @Test
    void listarViajeroCategoriaSinViajeros() {
        retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    void listarViajeroCategoriaSinCoincidencias() {
        s.registrarViajero("3.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("2.914.689-5", "Hugo", "hugo@ort.edu.uy", 35, Categoria.ESTANDAR);

        retorno = s.listarViajerosPorCategoria(Categoria.FRECUENTE);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }


    @Test
    void listarViajeroCategoriaConDuplicadosPorNombre() {
        s.registrarViajero("1.111.111-1", "Ana", "ana@ort.edu.uy", 40, Categoria.FRECUENTE);
        s.registrarViajero("1.111.111-2", "Ana", "dana@ort.edu.uy", 40, Categoria.FRECUENTE);

        retorno = s.listarViajerosPorCategoria(Categoria.FRECUENTE);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1.111.111-1;Ana;ana@ort.edu.uy;40;Frecuente|1.111.111-2;Ana;dana@ort.edu.uy;40;Frecuente", retorno.getValorString());
    }

    @Test
    void listarViajeroVariasCategorias() {
        s.registrarViajero("1.000.000-1", "Lucas", "lucas@ort.edu.uy", 25, Categoria.ESTANDAR);
        s.registrarViajero("2.000.000-2", "Sofi", "sofi@ort.edu.uy", 30, Categoria.FRECUENTE);
        s.registrarViajero("3.000.000-3", "Diego", "diego@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("4.000.000-4", "Ana", "ana@ort.edu.uy", 35, Categoria.ESTANDAR);

        retorno = s.listarViajerosPorCategoria(Categoria.FRECUENTE);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("2.000.000-2;Sofi;sofi@ort.edu.uy;30;Frecuente", retorno.getValorString());
    }



}

