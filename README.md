# Sistema kardex **"Hulk Store"**

Una tienda donde se desarrolla el emprendimiento que permite vender a sus empleados diferentes productos basados en superhéroes de Marvel y Dc Comics.

Entre los artículos se encuentran: 
- Camisetas
- Vasos
- Comics
- Juguetes
- Accesorios
- Varios más

## Sistema de kardex

El sistema está dividido en capas como es: 
- Modelo.- encargado del mapeo de entidades y enumeradores
- Dao.- que contiene las interfacces y clases de ser necesarias para el acceso a datos
- Servicio.- encargado de la logica del sistema y la transaccionalidad en las diferentes tablas y entidades.
- Controlador.- encargado de proveer los servicios rest para comunicación con los clientes frontend

El sistema se encuentra construido con java8, desplegable atravez de un archivo .jar y cuenta con la base en memoria H2