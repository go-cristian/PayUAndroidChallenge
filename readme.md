El proyecto cuenta con una arquitectura basada en "Clean Architecture" la cual divide el proyecto en 3 partes:

- Presentación: solo lógica visual
- Dominio: contratos y POJOs y logica de negocios.
- Datos: Preveedores de datos, en este proyecto solo usamos SQLite a travez de OrmLite. 

Presentación:
Este proyecto usa el patrón de diseño MVP donde separamos la logica de información de un activity/fragment a una clase java y se delega toda la lógica de presentación al encargado visual. Para la resolucion de dependencias se contempla un proveedor global usando la clase PayUApp que nos permite acceder a los singletons de la app. Un aproach similar al usado por sistemas como Dagger.

Dominio: 
Se declararón los contratos principales para acceder a la informacion (puede ser mejor, ya que se deberia tomar un enfoque reactivo en los cuales las interacciones tendran una respuesta y no son lineales).

Datos:
Se las usan tablas:

Client
- id -> entero
- name -> texto
- address -> texto
- phone -> texto

Account
- id -> entero
- value -> decimal
- client -> foranea a Client

Movement
- id -> entero
- tipo -> texto (pudo ser booleano, pero me gusta ser mas descriptivo con los nombres que se proveen)
- value -> decimal
- account -> foranea a Account
- client -> foranea a Client (puede ser innecesaria pero facilita el acceso 1-1 de las relaciones sin intermediar con mas querys)