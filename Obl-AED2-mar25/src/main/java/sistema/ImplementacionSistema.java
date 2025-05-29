package sistema;
import dominio.Conexion;
import tadsAux.ABB;
import tadsAux.GrafoCiudades;
import tadsAux.ListaImpl;
import tadsAux.ResultadoBusqueda;

import java.util.Comparator;
import java.util.List;

import dominio.Ciudad;
import dominio.Viajero;
import dominio.Vuelo;
import interfaz.*;


//CHRISTIAN CHARBONNIER 285506
public class ImplementacionSistema implements Sistema  {

    // ABB para búsquedas de viajeros
    private ABB<Viajero> viajerosPorCedula;
    private ABB<Viajero> viajerosPorCorreo;

    private GrafoCiudades grafoCiudades;

  //  private ListaImpl<Vuelo> vuelos;
    private Conexion conexion;

    private ListaImpl<Viajero> listaDeviajerosCi;

    // ABB para las ciudades
    private ListaImpl<Ciudad> ciudades;


    // ABB separados por categoría
    private ABB<Viajero> viajerosEstandar;
    private ABB<Viajero> viajerosFrecuente;
    private ABB<Viajero> viajerosPlatino;

    private ListaImpl<Viajero>[] viajerosPorRango;

    private int maxCiudades;

    @Override
    public Retorno inicializarSistema(int maxCiudades) {

        if (maxCiudades <= 4) {
            return Retorno.error1("La cantidad de ciudades debe ser mayor a 4");
        }

        this.maxCiudades = maxCiudades;

        // Comparadores personalizados
        viajerosPorCedula = new ABB<>(new Viajero.ComparadorPorCedula());
        viajerosPorCorreo = new ABB<>(new Viajero.ComparadorPorCorreo());

        ciudades = new ListaImpl<>(new Ciudad.ComparadorPorCodigo());

        // ABB por categoría (ordenados por cédula para poder listar en orden creciente)
        viajerosEstandar = new ABB<>(new Viajero.ComparadorPorCedula());
        viajerosFrecuente = new ABB<>(new Viajero.ComparadorPorCedula());
        viajerosPlatino = new ABB<>(new Viajero.ComparadorPorCedula());

        this.grafoCiudades = new GrafoCiudades(maxCiudades, true);

        this.conexion = new Conexion();
       // this.vuelos = new ListaImpl<>();

        listaDeviajerosCi = new ListaImpl<>();


        viajerosPorRango = (ListaImpl<Viajero>[]) new ListaImpl[14];

        for (int i = 0; i < 14; i++) {
            viajerosPorRango[i] = new ListaImpl<>();
        }



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


    //Metodo auxiliar para saber los rangos, van de 10 en 10
    private int obtenerRangoEdad(int edad) {
        if (edad < 0 || edad > 139)
            return -1;

        return edad / 10;
    }

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

        if (viajeroNuevo.getCategoria() == Categoria.ESTANDAR) {
            viajerosEstandar.insertar(viajeroNuevo);
        } else if (viajeroNuevo.getCategoria() == Categoria.FRECUENTE) {
            viajerosFrecuente.insertar(viajeroNuevo);
        } else if (viajeroNuevo.getCategoria() == Categoria.PLATINO) {
            viajerosPlatino.insertar(viajeroNuevo);
        }


        viajerosPorCorreo.insertar(viajeroNuevo);
        viajerosPorCedula.insertar(viajeroNuevo);

        int rangoEdad = obtenerRangoEdad(viajeroNuevo.getEdad());
        viajerosPorRango[rangoEdad].insertar(viajeroNuevo);

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

        if (!cedula.matches("^(\\d\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$")) {
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

        ABB<Viajero> abbCategoria;

        if (unaCategoria == Categoria.ESTANDAR) {
            abbCategoria = viajerosEstandar;
        } else if (unaCategoria == Categoria.FRECUENTE) {
            abbCategoria = viajerosFrecuente;
        } else if (unaCategoria == Categoria.PLATINO) {
            abbCategoria = viajerosPlatino;
        } else {
            return Retorno.error1("INGRESA UNA CATEGORIA VALIDA");
        }

        String resultado = abbCategoria.listarAscendente(); // Esto hace un recorrido inorden

        return Retorno.ok(resultado);
    }


    @Override
    public Retorno listarViajerosDeUnRangoAscendente(int rango) {
        if (rango < 0 || rango > 13)
            return Retorno.error1("El rango debe estar entre 0 y 13");

        ListaImpl<Viajero> listaViajeros = viajerosPorRango[rango];

        if (listaViajeros.esVacia()) {
            return Retorno.ok("");
        }

        listaViajeros.ordenarPor(new Viajero.ComparadorPorCedula());

        String listado = listaViajeros.toStringPersonalizado(v ->
                v.getCedula() + ";" +
                        v.getNombre() + ";" +
                        v.getCorreo() + ";" +
                        v.getEdad() + ";" +
                        v.getCategoria()
        );

        return Retorno.ok(listado);
    }

        /*
    Retorno registrarCiudad(String codigo, String nombre);
    Descripción: Registra una ciudad en el sistema con el código y nombre indicado. El código es el identificador
    único, el código y nombre no pueden ser vacíos ni null. Esta operación no tiene restricciones de eficiencia.
    Retornos posibles
    OK
    Si la ciudad fue registrada exitosamente.
    ERROR
    1. Si en el sistema ya hay registrados maxCuidades.
    2. Si código o nombre son vacíos o null.
    3. Si ya existe una ciudad con ese código.
    * */



    @Override
    public Retorno registrarCiudad(String codigo, String nombre) {

        int cantidadCiudades = ciudades.largo();
        //Objeto busqueda
        Ciudad ciudad = new Ciudad(codigo, nombre);

        //. Si en el sistema ya hay registrados maxCuidades.
        if( cantidadCiudades >= maxCiudades) {
            return Retorno.error1("No se pueden ingresar mas ciudades al sistema");
        }

        //Si código o nombre son vacíos o null.
        if(codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {

            return Retorno.error2("Codigo o nombre vacio o null");
        }

        if (ciudades.existe(ciudad)) {

            return Retorno.error3("Ya existe una ciudad registrada con ese codigo. Intenta nuevamente");
        }

        ciudades.insertar(ciudad);
        grafoCiudades.agregarCiudad(ciudad);



        return Retorno.ok();
    }


    @Override
    public Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino) {

        // Validación de parámetros vacíos o nulos
        if (codigoCiudadOrigen == null || codigoCiudadDestino == null ||
                codigoCiudadOrigen.isEmpty() || codigoCiudadDestino.isEmpty()) {
            return Retorno.error1("Parámetros inválidos");
        }

        // Crear objetos temporales para la búsqueda
        Ciudad ciudadOrigenTmp = new Ciudad(codigoCiudadOrigen, "");
        Ciudad ciudadDestinoTmp = new Ciudad(codigoCiudadDestino, "");

        // Buscar las ciudades en el ABB
        ResultadoBusqueda<Ciudad> origenResultado = ciudades.buscarConComparaciones(ciudadOrigenTmp);
        ResultadoBusqueda<Ciudad> destinoResultado = ciudades.buscarConComparaciones(ciudadDestinoTmp);

        Ciudad origen = origenResultado.getDato();
        Ciudad destino = destinoResultado.getDato();

        // Validaciones de existencia
        if (origen == null) return Retorno.error2("No existe la ciudad de Origen");
        if (destino == null) return Retorno.error3("No existe la ciudad de Destino");

        // Validar que no exista la conexión ya


        if (grafoCiudades.existeConexion(origen, destino)) {
            return Retorno.error4("Ya existe esa conexión");
        }

        // Insertar la conexión (una arista dirigida de origen -> destino)
        grafoCiudades.agregarConexion(origen, destino);

        return Retorno.ok();
    }

    //-----------------------------------------------------------------------------------

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {

        if (combustible <= 0 || minutos <= 0 || costoEnDolares <= 0)
            return Retorno.error1("Los parámetros double no pueden ser menores o iguales a cero");

        if (codigoCiudadOrigen == null || codigoCiudadOrigen.isEmpty()
                || codigoCiudadDestino == null || codigoCiudadDestino.isEmpty()
                || codigoDeVuelo == null || codigoDeVuelo.isEmpty())
            return Retorno.error2("String vacío o null");

//        Ciudad origen = grafoCiudades.buscarCiudad(codigoCiudadOrigen);
//        Ciudad destino = grafoCiudades.buscarCiudad(codigoCiudadDestino);

        // Crear objetos temporales para la búsqueda
        Ciudad ciudadOrigenTmp = new Ciudad(codigoCiudadOrigen, "");
        Ciudad ciudadDestinoTmp = new Ciudad(codigoCiudadDestino, "");

        // Buscar las ciudades en el ABB
        ResultadoBusqueda<Ciudad> origenResultado = ciudades.buscarConComparaciones(ciudadOrigenTmp);
        ResultadoBusqueda<Ciudad> destinoResultado = ciudades.buscarConComparaciones(ciudadDestinoTmp);

        Ciudad origen = origenResultado.getDato();
        Ciudad destino = destinoResultado.getDato();

        if (origen == null)
            return Retorno.error3("No existe la ciudad de origen");

        if (destino == null)
            return Retorno.error4("No existe la ciudad de destino");

        if (!grafoCiudades.existeConexion(origen, destino))
            return Retorno.error5("No existe conexión entre las dos ciudades");

        Conexion conexion = grafoCiudades.obtenerConexion(origen, destino);
        if (conexion.ExisteVuelo(codigoDeVuelo))
            return Retorno.error6("Ya existe un vuelo con ese código en esta conexión");

        Vuelo nuevo = new Vuelo(codigoDeVuelo, combustible, minutos, costoEnDolares, tipoDeVuelo);
        conexion.getVuelos().insertar(nuevo); // Insertamos el vuelo en la lista de vuelos que tengo en conexion

        return Retorno.ok();
    }



    @Override
    public Retorno actualizarVuelo(String codOrigen, String codDestino, String codVuelo,
                                   double combustible, double minutos, double costo,
                                   TipoVuelo tipo) {

        if (combustible <= 0 || minutos <= 0 || costo <= 0) return Retorno.error1("Valores invalidos");
        if (codOrigen == null || codDestino == null || codVuelo == null ||
                codOrigen.isEmpty() || codDestino.isEmpty() || codVuelo.isEmpty()) return Retorno.error2("Cadenas vacias");

        // Crear objetos temporales para la búsqueda
        Ciudad ciudadOrigenTmp = new Ciudad(codOrigen, "");
        Ciudad ciudadDestinoTmp = new Ciudad(codDestino, "");

        // Buscar las ciudades en el ABB
        ResultadoBusqueda<Ciudad> origenResultado = ciudades.buscarConComparaciones(ciudadOrigenTmp);
        ResultadoBusqueda<Ciudad> destinoResultado = ciudades.buscarConComparaciones(ciudadDestinoTmp);

        Ciudad origen = origenResultado.getDato();
        Ciudad destino = destinoResultado.getDato();


        if (origen == null) return Retorno.error3("Ciudad origen no existe");

        if (destino == null) return Retorno.error4("Ciudad destino no existe");

        if (!grafoCiudades.existeConexion(origen, destino)) return Retorno.error5("No hay conexion");

        Conexion conexion = grafoCiudades.obtenerConexion(origen, destino);

        if (!conexion.ExisteVuelo(codVuelo)) return Retorno.error6("No existe el vuelo");


        Vuelo vuelo = conexion.obtenerVuelo(codVuelo);

        vuelo.setCombustible(combustible);
        vuelo.setMinutos(minutos);
        vuelo.setCostoEnDolares(costo);
        vuelo.setTipoDeVuelo(tipo);

        return Retorno.ok();
    }

/*Descripción: Dada una ciudad de origen se debe retornar en el valorString los datos de las ciudades
(ordenadas por código creciente) a las que se pueda llegar realizando hasta la cantidad de escalas indicada
por parámetro. Esta operación no tiene restricciones de eficiencia.
Retornos posibles
OK
Retorna en valorString los datos de las ciudades a las que se pueda llegar con
hasta “cantidad” de escalas ordenadas de forma creciente por código.
ERROR
1. Si la cantidad es menor que cero.
2. Si el código es vacío o null.
3. Si la ciudad no está registrada en el sistema.
NO_IMPLEMENTADA Cuando aún no se implementó.
Formato de retorno del valor String:
codigoCiudad1;nombreCiudad1|codigoCiudad2;nombreCiudad2
Nota: Se debe cumplir que codigoCiudad1 es lexicográficamente menor a codigoCiudad2. */

    @Override
    public Retorno listadoCiudadesCantDeEscalas(String codigoCiudadOrigen, int cantidad) {
        if (cantidad < 0)
            return Retorno.error1("La cantidad debe ser mayor o igual a cero");

        if (codigoCiudadOrigen == null || codigoCiudadOrigen.trim().isEmpty())
            return Retorno.error2("Código ciudad origen vacío o null");

        Ciudad origen = grafoCiudades.buscarCiudad(codigoCiudadOrigen);
        if (origen == null)
            return Retorno.error3("La ciudad no está registrada en el sistema");

        // Caso especial: 0 escalas → devolver solo la ciudad origen
        if (cantidad == 0) {
            return Retorno.ok(origen.getCodigo() + ";" + origen.getNombre());
        }

        ListaImpl<Ciudad> alcanzables = grafoCiudades.obtenerCiudadesAlcanzables(codigoCiudadOrigen, cantidad);

        // Ordenar por código
        alcanzables.ordenarPor(Comparator.comparing(Ciudad::getCodigo));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alcanzables.largo(); i++) {
            Ciudad c = alcanzables.obtener(i);
            if (sb.length() > 0) sb.append("|");
            sb.append(c.getCodigo()).append(";").append(c.getNombre());
        }

        return Retorno.ok(sb.toString());
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