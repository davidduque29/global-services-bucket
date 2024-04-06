# Quickfinances
![Texto Alternativo](https://github.com/davidduque29/PruebaTecnicaQd/blob/369c9e16afed93af78bdc3aac40902dad36eb8c4/customer_finance_data/customer_finance_data/src/main/resources/images/logoQuickFinances.png?raw=true)

Aplicación de Gestión de Datos Financieros del Cliente
Esta aplicación de Spring Boot sirve como una solución integral para administrar los datos de los clientes y facilitar las transacciones financieras dentro de una institución financiera. Se adhiere a una arquitectura hexagonal, asegurando la modularidad y flexibilidad en el desarrollo y mantenimiento.

Descripción General
La aplicación atiende las siguientes funcionalidades:

###Gestión de Clientes: 

Registro, actualización y eliminación de la información del cliente.
Cálculo automático de la fecha de modificación al actualizar los detalles del cliente.
Aplicación de reglas de negocio como restricciones de edad y dependencias en productos vinculados.

###Gestión de Productos:

Creación de dos tipos de productos financieros: cuentas corrientes y cuentas de ahorros.
Generación automática de números de cuenta únicos con formatos predefinidos.
Mantenimiento del estado de la cuenta, incluida la activación, desactivación y cancelación.
Aplicación de reglas de negocio como saldo mínimo para cuentas de ahorro y restricciones en acciones de cuenta según el saldo.
Movimientos Transaccionales:


###Gestión de Transacciones 

Las transacciones financieras son una parte crucial de la aplicación, permitiendo realizar operaciones como consignaciones, retiros y transferencias entre cuentas. A continuación se detallan los requisitos y características asociadas a las transacciones:

Tipos de Transacciones:

Consignación (CONSIGNACION): Permite depositar fondos en una cuenta.
Retiro (RETIRO): Facilita la extracción de fondos de una cuenta.
Transferencia entre Cuentas (TRANSFERENCIA): Permite mover fondos de una cuenta a otra dentro del sistema.
Actualización de Saldos:

Con cada transacción realizada, la aplicación actualizará automáticamente el saldo y el saldo disponible de las cuentas involucradas.
Restricciones de Transferencia:

Las transferencias solo pueden realizarse entre cuentas existentes en el sistema.
Registro de Movimientos:

Al realizar una transferencia, se generan movimientos de crédito en la cuenta receptora y movimientos de débito en la cuenta emisora.
Es importante destacar que se debe implementar la estructura de persistencia necesaria en la base de datos para cumplir con todos los requisitos funcionales obligatorios asociados a las transacciones.

Este conjunto de funcionalidades garantiza una gestión eficiente y segura de las operaciones financieras dentro de la aplicación, proporcionando una experiencia robusta para los usuarios.

Soporte para diversas transacciones financieras, incluidos depósitos, retiros y transferencias entre cuentas.
Actualización en tiempo real de los saldos de cuenta y saldos disponibles con cada transacción.
Tecnologías Utilizadas
**Java:** El backend está desarrollado utilizando Java.
**Spring Boot:** Utilizado para un desarrollo rápido de aplicaciones y gestión de dependencias.
Base de Datos SQL: Elija entre SQL Server, Oracle, PostgreSQL o MySQL para la persistencia de datos.
Git: Sistema de control de versiones para gestionar iteraciones de proyectos y colaboración.
JUnit: Marco de trabajo para implementar pruebas unitarias con cobertura para capas de servicio y controlador.
Estructura del Proyecto
La aplicación sigue una arquitectura en capas con los siguientes componentes:

**Entidad:** Representa objetos de dominio y entidades de base de datos.
Servicio: Implementa la lógica empresarial y el comportamiento transaccional.
Controlador: Define los puntos finales de la API y maneja las solicitudes entrantes.
Repositorio: Gestiona el acceso a datos y la interacción con la base de datos.

Para Empezar
Clona el repositorio desde GitHub.
Configura los ajustes de la base de datos en el archivo de propiedades de la aplicación.
Compila y ejecuta la aplicación usando Maven o tu IDE preferido.
Accede a los puntos finales de la API para interactuar con los datos del cliente y realizar transacciones financieras.
**Pruebas**
La aplicación incluye pruebas unitarias escritas con JUnit para garantizar la fiabilidad y corrección de la lógica empresarial y los puntos finales de la API. Ejecuta las pruebas usando tu framework de pruebas preferido.

##Notas Adicionales
Asegúrate de mantener una documentación y comentarios adecuados en todo el código.
Realiza commits y push regularmente a repositorio Git para rastrear el progreso del proyecto de manera efectiva.
Colaboradores
[Ivan David Duque Perdomo]
Para obtener instrucciones detalladas sobre cómo configurar el proyecto, consulta la documentación.
