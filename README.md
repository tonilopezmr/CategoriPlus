CategoriPLus+
=====================

Creado por Antonio López Marín como trabajo final para Desarrollo de Interfaces.

Aplicación que crea y modifica categorias y productos y genera informes mendiante JasperReport, se conecta
a una base de datos, esta optimizada para conectarse a OpenERP.

Idiomas 
=====================

Esta en español e ingles.


Metodos de desarrollo
=====================

Se ha desarrollado utilizando el patron DAO + Singleton + Factory, para conectarse a cualquier base de datos, con el mismo nombre de schema y tablas para poder generar correctamente los informes.

Para la interfaz se utilizo el patron Observer para notificar a la vista que el modelo cambio.

Test JUnit + FEST Swing
=====================

Se crearon unos test de ejemplo del modelo de la conexion a la base de datos y la interfaz con JUnit y Fest para Swing.

Librerias Utilizadas
====================

Se han utilizado las siguientes librerias:

	- Simple Validation API
	- OpenERP Java Client 
	- JDBC Postgresql
	- Fest Swing
	- JasperReport
	- JavaHelp
	- Y un boton personalizado Swing mio

Ayuda
===================

Tiene un sistema de ayuda creado con JavaHelp.

