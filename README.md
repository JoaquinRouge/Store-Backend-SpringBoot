Proyecto Backend con Spring Boot

Descripción:

Este es un proyecto backend desarrollado con Spring Boot que permite gestionar clientes, productos y ventas. Implementa un CRUD completo
para cada entidad y maneja la persistencia de datos mediante JPA e Hibernate con MySQL.

Tecnologías Utilizadas

Java 23

Spring Boot (Spring MVC, Spring Data JPA)

Hibernate

MySQL

Maven

Instalación y Configuración

Clonar el repositorio:

git clone https://github.com/JoaquinRouge/Store-Backend-SpringBoot.git

Configurar la base de datos en application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/nombre_bd
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

Construir y ejecutar la aplicación:

mvn spring-boot:run

Uso

Endpoints principales

Clientes:

GET:

/clients/all : Trae todos los clientes existentes en la base de datos.

/clients/get/{id} : Recibe un id de tipo Long por medio de la URL y trae el cliente con el mismo id desde la base de datos.

POST:

/clients/save: Recibe un cliente por parametro y lo agrega a la base de datos.

DELETE:

/clients/delete/{id} : Recibe un id de tipo Long por medio de la URL y elimina de la base de datos el cliente que tenga el mismo id.

PUT:

/clients/edit : Recibe un cliente por parametro y al buscarlo mediante su Id lo edita en la base de datos.

Productos:

GET:

/products/all : Trae todos los productos existentes en la base de datos.

/products/get/{id} : Recibe un id de tipo Long por medio de la URL y trae el cliente con el mismo id desde la base de datos.

/products/lowstock : Devuelve una lista de todos los productos que tengan un stock menor al umbral establecido

POST:

/products/save: Recibe un producto por parametro y lo agrega a la base de datos.

DELETE:

/products/delete/{id} : Recibe un id de tipo Long por medio de la URL y elimina de la base de datos el cliente que tenga el mismo id.

PUT:

/products/edit : Recibe un cliente por parametro y al buscarlo mediante su Id lo edita en la base de datos.

Ventas:

GET:

/sales/all : Trae todos las ventas existentes en la base de datos.

/sales/get/{id} : Recibe un id de tipo Long por medio de la URL y trae la venta con el mismo id desde la base de datos.

/sales/products/{saleId} : Recibe el Id de una venta y devuelve todos los productos de esa venta.

/sales/date/{date} : Recibe una fecha con formato dd-MM-yyyy y devuelve todas las ventas realizadas en ese dia.

POST:

/sales/save: Recibe una venta por parametro y la agrega a la base de datos.

DELETE:

/sales/delete/{id} : Recibe un id de tipo Long por medio de la URL y elimina de la base de datos la venta que tenga el mismo id.

PUT:

/sales/edit : Recibe un cliente por parametro y al buscarlo mediante su Id lo edita en la base de datos.


Puedes probar los endpoints usando Postman o herramientas similares.

El proyecto tiene tambien un front end realizado por mi. No tiene todas las funcionalidades puesto que fue meramente hecho para poner a prueba algunas de las
funcionalidades de la API (No todas, faltan implementar las Vistas y CRUD de Sale) y lo que me interesa es mostar mis habilidades actuales en desarrollo backend y no frontend.

Autor:

Joaquín Rougé Núñez

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


Backend Project with Spring Boot

Description:

This is a backend project developed with Spring Boot that allows to manage customers, products and sales. It implements a complete CRUD for each entity and handles data persistence using JPA and Hibernate with MySQL.

Technologies Used

Java 23

Spring Boot (Spring MVC, Spring Data JPA)

Hibernate

MySQL

Maven

Installation and Configuration

Clone the repository:

git clone https://github.com/JoaquinRouge/Store-Backend-SpringBoot.git

Configure the database in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/bd_name spring.datasource.username=your_user spring.datasource.password=your_password spring.jpa.hibernate.ddl-auto=update

Build and run the application:

mvn spring-boot:run

Usage

Main endpoints

Clients:

GET:

/clients/all : Fetch all existing clients in the database.

/clients/get/{id} : Receives an id of type Long via URL and fetches the client with the same id from the database.

POST:

/clients/save: Receives a client by parameter and adds it to the database.

DELETE:

/clients/delete/{id} : Receives an id of type Long by URL and deletes the client with the same id from the database.

PUT:

/clients/edit : Receives a client by parameter and by searching for it by its Id edits it in the database.

Products:

GET:

/products/all : Get all existing products in the database.

/products/get/{id} : Receives an id of type Long by means of the URL and fetches the client with the same id from the database.

/products/lowstock : Returns a list of all products with stock less than the set threshold.

POST:

/products/save: Receives a product by parameter and adds it to the database.

DELETE:

/products/delete/{id} : Receives an id of type Long by URL and deletes the customer with the same id from the database.

PUT:

/products/edit : Receives a customer by parameter and when searching for it by its Id edits it in the database.

Sales:

GET:

/sales/all : Get all existing sales in the database.

/sales/get/{id} : Receives an id of type Long by means of the URL and fetches the sale with the same id from the database.

/sales/products/{saleId} : Receives the id of a sale and returns all the products of that sale.

/sales/date/{date} : Receives a date with format dd-MM-yyyy and returns all sales made on that day.

POST:

/sales/save: Receives a sale by parameter and adds it to the database.

DELETE:

/sales/delete/{id} : Receives an id of type Long by URL and deletes the sale with the same id from the database.

PUT:

/sales/edit : Receives a customer by parameter and by searching it by its Id edits it in the database.

You can test the endpoints using Postman or similar tools.

The project has also a front end made by me. It doesn't have all the functionalities since it was merely made to test some of the API functionalities (Not all, we are missing to implement Sale Views and CRUD) and what I am interested in is to show my current skills in backend and not frontend development.

Author:

Joaquín Rougé Núñez
