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
        void listarViajeroCorreosOk() {
            s.registrarViajero("3.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
            s.registrarViajero("2.914.689-5", "Hugo", "hugo@ort.edu.uy", 35, Categoria.ESTANDAR);
            s.registrarViajero("5.914.689-5", "Guillermo", "asma@ort.edu.uy", 35, Categoria.ESTANDAR);

            retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);

            assertEquals(Retorno.Resultado.OK, retorno.getResultado());

            // assertEquals(1, retorno.getValorInteger());

            assertEquals("2.914.689-5;Hugo;hugo@ort.edu.uy;35;Estándar|3.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar|5.914.689-5;Guillermo;asma@ort.edu.uy;35;Estándar", retorno.getValorString());

            System.out.println(retorno);


        }
    }

