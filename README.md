# Sistema de Gestión de Viajeros ✈️

Este proyecto es una aplicación Java orientada a objetos que permite registrar y gestionar viajeros, realizar búsquedas eficientes, y listar datos ordenadamente utilizando estructuras de datos avanzadas como árboles binarios de búsqueda (ABB) y árboles AVL.

## 🧩 Tecnologías utilizadas

- Java 17+
- JUnit 5 (Testing)
- Git y GitHub (Control de versiones)

## 🌳 Estructuras de datos utilizadas

- **Árbol Binario de Búsqueda (ABB)**: Para almacenar y ordenar viajeros por número de cédula.
- **Árbol AVL**: Para almacenar y ordenar viajeros por correo electrónico de forma balanceada.

Estas estructuras permiten búsquedas, inserciones y listados eficientes en tiempo logarítmico.

## 📋 Funcionalidades implementadas

- ✅ Registro de viajeros con validaciones (cédula, edad, correo)
- ✅ Búsqueda de viajero por correo (AVL)
- ✅ Listado de viajeros por cédula o correo en orden ascendente
- ✅ Detección de duplicados por correo y cédula
- 🚧 Otras funcionalidades en desarrollo

## 🧪 Testing

Este proyecto cuenta con pruebas unitarias en JUnit 5, incluyendo:

```java
@Test
void listarViajeroOk() {
    s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
    retorno = s.buscarViajeroPorCorreo("guille@ort.edu.uy");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
}
