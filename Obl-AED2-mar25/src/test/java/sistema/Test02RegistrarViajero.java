package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test02RegistrarViajero {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void registrarViajeroOk() {
        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    /*1. Si alguno de los parámetros es vacío o null.
 */
    @Test
    void registrarViajeroError1() {
        retorno = s.registrarViajero("", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero(null, "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", null, "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", null, 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    //------------------------------------------------------------------------------------------------------------------
    /*2. Si la cédula no es una cédula con formato válido.*/
    @Test
    void registrarViajeroError2() {
        retorno = s.registrarViajero("1.91.4.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("1.914.6895", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("1914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    //------------------------------------------------------------------------------
    //3. Si el correo no tiene el formato válido.
    @Test
    void registrarViajeroError3() {
        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guilleAortedu", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guilleorteduu@", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "@guilleort.edu.u", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    //-------------------------------------------------------------------------------------------------------
    //4. Si la edad no está en el rango válido [0 ~ 139] límites incluidos.
    @Test
    void registrarViajeroError4() {
        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 211, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 1449, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", -1, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 140, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }


    //------------------------------------------------------------------------------------------------------
    // 5. Si ya existe un viajero registrado con esa cédula.
    @Test
    void registrarViajeroError5() {

        //registro exitoso
        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());



        //registro repetido
        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());

    }

    //----------------------------------------------------------------------------------------------------------------
    //6. Si ya existe un viajero registrado con ese correo.
    @Test
    void registrarViajeroError6() {

        //registro exitoso
        retorno = s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());



        //registro repetido
        retorno = s.registrarViajero("3.934.899-4", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());

    }

}
