package sistema;
import abb.ABB;
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

        // Inicialización de las estructuras requeridas
        viajerosPorCedula = new ABB<>();
        viajerosPorCorreo = new ABB<>(Viajero.comparadorPorCorreo);


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
        Viajero viajeroBuscado = new Viajero(cedula, nombre, correo, edad, categoria);


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

        if (viajerosPorCedula.buscar(viajeroBuscado) !=  null){

            return Retorno.error5("Ya existe un viajero registrado con esa cedula");
        }

        if (viajerosPorCorreo.buscar(viajeroBuscado) != null){

            return Retorno.error6("Ya existe un viajero registrado con ese email");
        }

        Viajero nuevo = new Viajero(cedula, nombre, correo, edad, categoria);

        viajerosPorCorreo.insertar(nuevo);
        viajerosPorCedula.insertar(nuevo);

        return Retorno.ok();
    }

    //-------------------------------------------------------------------------------------------------

    @Override
    public Retorno buscarViajeroPorCedula(String cedula) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarViajeroPorCorreo(String correo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosPorCedulaAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosPorCedulaDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosPorCorreoAscendente() {
        return Retorno.noImplementada();
    }

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
