--insert tabla CATEGORIAS
insert into Categorias (nombre, descripcion, estado) values ('Camisetas', 'Camisetas de superheroes','ACT');
insert into Categorias (nombre, descripcion, estado) values ('Vasos', 'Vasos de superheroes','ACT');
insert into Categorias (nombre, descripcion, estado) values ('Comics', 'Comics de superheroes de Marvel y DC','ACT');
insert into Categorias (nombre, descripcion, estado) values ('Juguetes', 'Juguetes y personajes de superheroes','ACT');
insert into Categorias (nombre, descripcion, estado) values ('Accesorios', 'Accesorios varios','ACT');

--insert tabla PRODUCTOS

insert into Productos (nombre, stock, stock_emergencia, precio_compra, precio_venta, id_categoria, estado) values ('Camiseta Hulk talla M', 10, 2, 15.5, 21, 1, 'ACT');
insert into Productos (nombre, stock, stock_emergencia, precio_compra, precio_venta, id_categoria, estado) values ('Camiseta Hulk talla S', 8, 1,13.5, 17, 1, 'ACT');
insert into Productos (nombre, stock, stock_emergencia, precio_compra, precio_venta, id_categoria, estado) values ('Camiseta Hulk talla L', 20, 2, 15.5, 21,1, 'ACT');

insert into Productos (nombre, stock, stock_emergencia, precio_compra, precio_venta, id_categoria, estado) values ('Vaso Thor 12 onz', 1, 0,  8, 15,  2,'ACT');

insert into Productos (nombre, stock, stock_emergencia, precio_compra, precio_venta, id_categoria, estado) values ('Colgante martillo de Thor', 5, 1, 1.12, 4,  5,'ACT');

--insert tabla Personas

insert into Personas (ciudad, correo_electronico, direccion, identificacion, nombre, telefono, tipo_persona, username, password, estado) values ('QUITO', 'consumidor@final.com', 'Quito', '9999999999', 'Consumidor final', '0', 'CLI', 'consumidor', '', 'ACT');   

