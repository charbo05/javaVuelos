package sistema;
import tadsAux.ABB;
import tadsAux.ResultadoBusqueda;
import dominio.Ciudad;
import dominio.Viajero;
import interfaz.*;


//CHRISTIAN CHARBONNIER 285506
public class ImplementacionSistema implements Sistema  {

    //tenemos las dos posibilidades viajero por correo o ci
    private ABB<Viajero> viajerosPorCedula;
    private ABB<Viajero> viajerosPorCorreo;
    private int maxCiudades;
    private ABB<Ciudad> ciudades;




    /*01.- Inicializar Sistema

    Retorno inicializarSistema (int maxCiudades);

    Descripción: Inicializa las estructuras necesarias para representar el sistema especificado, capaz de registrar
    como máximo la cantidad maxCiudades de ciudades diferentes en el sistema.

    Restricción de eficiencia: no tiene.

    Retornos posibles
    OK Si El sistema pudo ser inicializado exitosamente.
    ERROR 1. Si maxCiudades es menor o igual a 4.
    NO_IMPLEMENTADA Cuando aún no se implementó. Es el tipo de retorno por defecto.*/
    @Override
    public Retorno inicializarSistema(int maxCiudades) {

        // Validación del requisito mínimo
        if (maxCiudades <= 4) {
            return Retorno.error1("La cantidad de ciudades debe ser mayor a 4");
        }

        this.maxCiudades = maxCiudades;
        // Inicialización de las estructuras requeridas
        viajerosPorCedula = new ABB<>(new Viajero.ComparadorPorCedula());
        viajerosPorCorreo = new ABB<>(new Viajero.ComparadorPorCorreo());


        ciudades = new ABB<>();

        // Devolver retorno exitoso
        return Retorno.ok();
    }

    //-----------------------------------------------------------------------

    /*Descripción: Registra el viajero con sus datos, la cédula y el correo son únicos.
    Restricción de eficiencia: Esta operación deberá realizarse en orden O(log n) promedio, siendo n la cantidad
    total de viajeros.
    Retornos posibles
    OK Si El viajero fue registrado exitosamente.
    ERROR
    1. Si alguno de los parámetros es vacío o null.
    2. Si la cédula no es una cédula con formato válido.
    3. Si el correo no tiene el formato válido.
    4. Si la edad no está en el rango válido [0 ~ 139] límites incluidos.
    5. Si ya existe un viajero registrado con esa cédula.
    6. Si ya existe un viajero registrado con ese correo.
    NO_IMPLEMENTADA Cuando aún no se implementó. */


    @Override
    public Retorno registrarViajero(String cedula, String nombre, String correo, int edad, Categoria categoria) {

        //Creamos el viajero para buscar en el ABB
        Viajero viajeroNuevo = new Viajero(cedula, nombre, correo, edad, categoria);


        if (    cedula == null || cedula.isEmpty() || nombre == null ||
                nombre.isEmpty() || correo == null || correo.isEmpty() ||
                categoria == null )

        {
           return Retorno.error1("Debe completar todos los parametros");
        }

        if (!cedula.matches("^(\\d\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$")) {
            return Retorno.error2("Cédula no es una cédula con formato válido.");
        }

        if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")){
            return Retorno.error3("Correo no tiene el formato válido");
        }

        if (edad<0 || edad>139){
            return Retorno.error4("edad no está en el rango válido [0 ~ 139] límites incluidos.");
        }

        if (viajerosPorCedula.buscar(viajeroNuevo) !=  null){

            return Retorno.error5("Ya existe un viajero registrado con esa cedula");
        }

        if (viajerosPorCorreo.buscar(viajeroNuevo) != null){

            return Retorno.error6("Ya existe un viajero registrado con ese email");
        }



        viajerosPorCorreo.insertar(viajeroNuevo);
        viajerosPorCedula.insertar(viajeroNuevo);

        return Retorno.ok();
    }

    //-------------------------------------------------------------------------------------------------

    /*

    03.- Buscar Viajero por cédula
    Retorno buscarViajeroPorCedula(String cedula);
    Descripción: Retorna en valorString los datos del viajero con el formato
    “cedula;nombre;correo;edad;categoria”. Además, en el campo valorEntero del objeto retorno deberá
    devolver la cantidad de elementos que recorrió durante la búsqueda en la estructura utilizada.
    Restricción de eficiencia: Esta operación deberá realizarse en orden O(log n) promedio, siendo n la
    cantidad total de viajeros.
    Retornos posibles
    OK
    ERROR
    Si el viajero se encontró.
    Retorna en valorString los datos del viajero.
    Retorna en valorEntero la cantidad de elementos recorridos durante la búsqueda.
    1. Si la cédula es vacía o null.
    2. Si la cédula no tiene formato válido.
    3. Si no existe un viajero registrado con esa cédula.
    NO_IMPLEMENTADA Cuando aún no se implementó.
    Formato de retorno del valorString: cedula;nombre;correo;edad;categoría.
    Por ejemplo, valorString del retorno en una consulta válida:
    1.914.689-5;Guillermo;guille@ort.edu.uy,35,Estándar
         */

    @Override
    public Retorno buscarViajeroPorCedula(String cedula) {
        // Validaciones iniciales
        if (cedula == null || cedula.isEmpty()) {
            return Retorno.error1("La cédula no puede estar vacía");
        }

        if (!cedula.matches("^(\\d{1,2}\\.\\d{3}\\.\\d{3}-\\d)$")) {
            return Retorno.error2("Cédula no tiene un formato válido");
        }

        // Creamos un viajero ficticio para comparar (solo se usa la cédula en el compareTo)
        Viajero buscado = new Viajero(cedula, "", "", 0, Categoria.ESTANDAR);

        // Usamos el ABB para buscar devolviendo también el contador de comparaciones
        ResultadoBusqueda<Viajero> resultado = viajerosPorCedula.buscarConComparaciones(buscado);

        if (resultado.getDato() == null) {
            return Retorno.error3("El viajero no está registrado");
        }

        // Construimos el string de retorno
        Viajero encontrado = resultado.getDato();
        String datos = encontrado.getCedula() + ";" +
                encontrado.getNombre() + ";" +
                encontrado.getCorreo() + ";" +
                encontrado.getEdad() + ";" +
                encontrado.getCategoria().toString();

        // Devolvemos OK con comparaciones realizadas y el string
        return Retorno.ok(resultado.getComparaciones(), datos);
    }

    //------------------------------------------------------------------------------------------


    @Override
    public Retorno buscarViajeroPorCorreo(String correo) {

        // Validaciones iniciales
        if (correo == null || correo.isEmpty()) {
            return Retorno.error1("El correo no puede estar vacía");
        }

        if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            return Retorno.error2("Correo no tiene un formato válido");
        }

        // Creamos un viajero ficticio para comparar (solo se usa el correo en el compareTo)
        Viajero buscado = new Viajero("", "", correo, 0, Categoria.ESTANDAR);

        // Usamos el ABB para buscar devolviendo también el contador de comparaciones
        ResultadoBusqueda<Viajero> resultado = viajerosPorCorreo.buscarConComparaciones(buscado);

        if (resultado.getDato() == null) {
            return Retorno.error3("El viajero no está registrado");
        }

        // Construimos el string de retorno
        Viajero encontrado = resultado.getDato();
        String datos = encontrado.getCedula() + ";" +
                encontrado.getNombre() + ";" +
                encontrado.getCorreo() + ";" +
                encontrado.getEdad() + ";" +
                encontrado.getCategoria().toString();

        // Devolvemos OK con comparaciones realizadas y el string
        return Retorno.ok(resultado.getComparaciones(), datos);
    }

//---------------------------------------------------------------------------------

    @Override
    public Retorno listarViajerosPorCedulaAscendente() {
        if (viajerosPorCedula.esVacia()) {
            return Retorno.ok(""); // OK, pero sin contenido
        }

        String resultado = viajerosPorCedula.listarAscendente(); // este debe devolver el String concatenado
        return Retorno.ok(resultado);
    }

//----------------------------------------------------------------------------------------


    @Override
    public Retorno listarViajerosPorCedulaDescendente() {

        if (viajerosPorCedula.esVacia()) {
            return Retorno.ok(""); // OK, pero sin contenido
        }

        String resultado = viajerosPorCedula.listarDescendente(); // este debe devolver el String concatenado
        return Retorno.ok(resultado);
    }


    //------------------------------------------------------------------------------------------------
    @Override
    public Retorno listarViajerosPorCorreoAscendente() {


        if (viajerosPorCorreo.esVacia()) {
            return Retorno.ok(""); // OK, pero sin contenido
        }

        String resultado = viajerosPorCorreo.listarAscendente(); // este debe devolver el String concatenado
        return Retorno.ok(resultado);
    }


    //-------------------------------------------------------------------------------------------------
    @Override
    public Retorno listarViajerosPorCategoria(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosDeUnRangoAscendente(int rango) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCiudad(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoCiudadesCantDeEscalas(String codigoCiudadOrigen, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoMinutos(String codigoCiudadOrigen, String codigoCiudadDestino, TipoVueloPermitido tipoVueloPermitido) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoDolares(String codigoCiudadOrigen, String codigoCiudadDestino, TipoVueloPermitido tipoVueloPermitido) {
        return Retorno.noImplementada();
    }

}
