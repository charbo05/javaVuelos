package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test07ListarViajerosPorCorreoAscendente {



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

        retorno = s.listarViajerosPorCorreoAscendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // assertEquals(1, retorno.getValorInteger());

        assertEquals("5.914.689-5;Guillermo;asma@ort.edu.uy;35;Estándar|3.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar|2.914.689-5;Hugo;hugo@ort.edu.uy;35;Estándar", retorno.getValorString());

            System.out.println(retorno);


    }
        /*1.914.689-5
         * 2.914.689-5*/
        @Test
        void listarViajeroCorreoDesordenadosOk() {
        s.registrarViajero("2.914.689-5", "Guillermo", "biron@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.914.689-5", "Guillermo", "guille2@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("3.914.689-5", "Guillermo", "antunez3@ort.edu.uy", 35, Categoria.ESTANDAR);

        retorno = s.listarViajerosPorCorreoAscendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());



        assertEquals("3.914.689-5;Guillermo;antunez3@ort.edu.uy;35;Estándar|2.914.689-5;Guillermo;biron@ort.edu.uy;35;Estándar|1.914.689-5;Guillermo;guille2@ort.edu.uy;35;Estándar", retorno.getValorString());


            System.out.println(retorno);

    }

    @Test
    void listarViajerosPorCorreoAscendente_variosViajeros_ordenCorrecto() {
        s.inicializarSistema(5);

        s.registrarViajero("4.000.000-0", "Ana", "zzz@correo.com", 22, Categoria.ESTANDAR);
        s.registrarViajero("3.000.000-0", "Luis", "aaa@correo.com", 30, Categoria.PLATINO);
        s.registrarViajero("5.000.000-0", "Sofía", "mmm@correo.com", 27, Categoria.FRECUENTE);

        Retorno retorno = s.listarViajerosPorCorreoAscendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(
                "3.000.000-0;Luis;aaa@correo.com;30;Platino|" +
                        "5.000.000-0;Sofía;mmm@correo.com;27;Frecuente|" +
                        "4.000.000-0;Ana;zzz@correo.com;22;Estándar",
                retorno.getValorString()
        );
        System.out.println(retorno);
    }

    @Test
    void listarViajerosPorCorreoAscendente_sinViajeros() {
        s.inicializarSistema(5);

        Retorno retorno = s.listarViajerosPorCorreoAscendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }


}

