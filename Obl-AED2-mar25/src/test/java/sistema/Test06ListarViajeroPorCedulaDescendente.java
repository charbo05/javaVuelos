package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test06ListarViajeroPorCedulaDescendente {

        private Retorno retorno;
        private final Sistema s = new ImplementacionSistema();

        @BeforeEach
        public void setUp() {
            s.inicializarSistema(10);
        }

        @Test
        void listarViajeroDescendenteOk() {
            s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
            s.registrarViajero("2.914.689-5", "Guillermo", "guille2@ort.edu.uy", 35, Categoria.ESTANDAR);
            s.registrarViajero("3.914.689-5", "Guillermo", "guille3@ort.edu.uy", 35, Categoria.ESTANDAR);

            retorno = s.listarViajerosPorCedulaDescendente();

            assertEquals(Retorno.Resultado.OK, retorno.getResultado());

            // assertEquals(1, retorno.getValorInteger());

            assertEquals("3.914.689-5;Guillermo;guille3@ort.edu.uy;35;Estándar|2.914.689-5;Guillermo;guille2@ort.edu.uy;35;Estándar|1.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar", retorno.getValorString());



        }
        /*1.914.689-5
         * 2.914.689-5*/
        @Test
        void listarViajeroDesordenadosOk() {
            s.registrarViajero("2.914.689-5", "Guillermo", "guille2@ort.edu.uy", 35, Categoria.ESTANDAR);
            s.registrarViajero("3.914.689-5", "Guillermo", "guille3@ort.edu.uy", 35, Categoria.ESTANDAR);
            s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);

            retorno = s.listarViajerosPorCedulaDescendente();

            assertEquals(Retorno.Resultado.OK, retorno.getResultado());

            // assertEquals(1, retorno.getValorInteger());

            assertEquals("3.914.689-5;Guillermo;guille3@ort.edu.uy;35;Estándar|2.914.689-5;Guillermo;guille2@ort.edu.uy;35;Estándar|1.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar", retorno.getValorString());



        }
    }

