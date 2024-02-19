# Sales Project

Este proyecto es la entrega final para el curso "Programación en Java". Se centra en el desarrollo de una aplicación para administrar productos, clientes y ventas en un negocio. La aplicación incluye relaciones entre estos elementos y utiliza una base de datos MySQL para almacenar la información.

### Funcionalidades principales
- **Administración de Productos:** Permite agregar, modificar y eliminar productos del inventario. Permite tener y manipular información sobre el nombre, código, precio, cantidad y descripción del mismo.
- **Administración de Clientes: **Permite gestionar la información de los clientes, como su nombre, apellido, dni y contacto.
- **Administración de Ventas:** Facilita la creación de nuevas ventas, con la posibilidad de asociar productos y clientes a cada venta.

Todas las entidades cuentan con un *id* único para poder manipular la información de cada una de ellas.

### Tecnologías utilizadas
El proyecto utiliza las siguientes tecnologías y herramientas:

- Spring Boot: Framework de desarrollo para Java que facilita la creación de aplicaciones web.
- Spring Web: Módulo de Spring que simplifica el desarrollo de aplicaciones web basadas en Spring.
- Spring Data JPA: Facilita el acceso a datos relacionales a través de JPA en aplicaciones Spring.
- Lombok: Biblioteca que reduce la cantidad de código boilerplate (repetitivo) en Java, como los métodos getters y setters.
- MySQL: Sistema de gestión de bases de datos relacional utilizado para almacenar la información del proyecto.
- Postman: Herramienta para probar y documentar APIs.
- REST (Representational State Transfer): Estilo arquitectónico para el desarrollo de servicios web que se utilizó para crear la API RESTful del proyecto.
- Invocación de servicio REST externo: Se integra un servicio externo REST para manejar la fecha de las ventas de manera automática.

### Controladores del Sistema de Gestión de Ventas

##### Controlador de Ventas
El controlador de ventas gestiona las operaciones relacionadas con las ventas en el sistema. Proporciona endpoints para crear, obtener, modificar y eliminar ventas. Además, permite realizar operaciones como la creación de nuevas ventas, la eliminación de ventas existentes y la actualización de información de ventas.

##### Controlador de Productos
El controlador de productos se encarga de las operaciones relacionadas con los productos en el sistema. Ofrece endpoints para crear, obtener, modificar y eliminar productos. Esto incluye funciones como agregar nuevos productos al inventario, actualizar la información de los productos y eliminar productos del inventario.

##### Controlador de Clientes
El controlador de clientes maneja las operaciones relacionadas con los clientes en el sistema. Proporciona endpoints para crear, obtener, modificar y eliminar clientes. Esto implica funciones como registrar nuevos clientes en la base de datos, actualizar la información de los clientes y eliminar clientes existentes del sistema.

Después de implementar los controladores, se ha generado una documentación detallada de la API utilizando Swagger. Puedes acceder a la documentación completa, que incluye ejemplos y descripciones de cada endpoint, visitando el siguiente enlace:

[Documentación Swagger](http://localhost:8080/swagger-ui/index.html#/ "Documentación Swagger")

En esta documentación, podrás explorar todos los endpoints disponibles, sus parámetros, tipos de respuesta y ejemplos de uso.

### Instalación y Uso
1. Clona el repositorio en tu máquina local.
2. Configura la base de datos MySQL según las especificaciones del archivo de configuración.
3. Ejecuta la aplicación Spring Boot.
4. Accede a la interfaz de usuario o utiliza las API RESTful proporcionadas para interactuar con el sistema.

