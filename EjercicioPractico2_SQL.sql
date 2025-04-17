drop schema if exists cine_teatro_db;
drop user if exists usuario_general;

-- Crear usuario de conexión
CREATE USER 'usuario_general'@'%' IDENTIFIED BY 'Usuar1o_Clave.';

-- Crear base de datos
CREATE DATABASE cine_teatro_db;

-- Otorgar permisos
GRANT ALL PRIVILEGES ON cine_teatro_db.* TO 'usuario_general'@'%';
FLUSH PRIVILEGES;

-- Seleccionar la base de datos
USE cine_teatro_db;

-- Tabla de usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100) UNIQUE,
    contrasena VARCHAR(255),
    rol VARCHAR(20)
);

-- Tabla de películas u obras
CREATE TABLE peliculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150),
    tipo VARCHAR(20),-- peli o obra
    ruta_imagen varchar(1024),
	activo boolean
);

-- Tabla de funciones
CREATE TABLE funciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pelicula INT,
    fecha DATE,
    hora TIME,
    precio double,
    sala VARCHAR(50),
     activo boolean,
    FOREIGN KEY (id_pelicula) REFERENCES peliculas(id)
);

-- Tabla de reservas
CREATE TABLE reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_funcion INT,
    cantidad INT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_funcion) REFERENCES funciones(id)
);
-- Insertar usuarios de ejemplo
INSERT INTO usuarios (nombre, correo, contrasena, rol) VALUES
('Ana López', 'ana@cine.com', 'clave123', 'USUARIO'),
('Carlos Pérez', 'carlos@cine.com', 'clave456', 'USUARIO'),
('Admin Cine', 'admin@cine.com', 'admin123', 'ADMIN');

-- Insertar películas/obras
INSERT INTO peliculas (titulo, tipo,activo,ruta_imagen) VALUES
('El Rey León', 'OBRA',true,''),
('Avengers: Endgame', 'PELICULA',true,''),
('La Bella y la Bestia', 'OBRA',true,''),
('Spider-Man: No Way Home', 'PELICULA',true,'');

-- Insertar funciones
INSERT INTO funciones (id_pelicula, fecha, hora, sala, activo, precio) VALUES
(1, '2025-04-20', '18:00:00', 'Sala A',true,'1000'),
(2, '2025-04-21', '20:00:00', 'Sala B',true,'2000'),
(3, '2025-04-22', '17:00:00', 'Sala C',true,'3000'),
(4, '2025-04-23', '21:00:00', 'Sala D',true,'4000');

-- Insertar reservas
INSERT INTO reservas (id_usuario, id_funcion, cantidad) VALUES
(1, 1, 2),
(2, 2, 1),
(1, 4, 3);

create table rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre varchar(20),
  id_usuario int,
  PRIMARY KEY (id_rol),
  foreign key fk_rol_usuario (id_usuario) references usuarios(id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

insert into rol (id_rol, nombre, id_usuario) values
 (1,'ROLE_ADMIN',1), (2,'ROLE_VENDEDOR',1), (3,'ROLE_USER',1),
 (4,'ROLE_VENDEDOR',2), (5,'ROLE_USER',2),
 (6,'ROLE_USER',3);


CREATE TABLE request_matcher (
    id_request_matcher INT AUTO_INCREMENT NOT NULL,
    pattern VARCHAR(255) NOT NULL,
    role_name VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_request_matcher))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO request_matcher (pattern, role_name) VALUES ('/producto/nuevo', 'ADMIN'),
('/producto/guardar', 'ADMIN'),
('/producto/modificar/** ', 'ADMIN'),
('/producto/eliminar/**', 'ADMIN'),
('/categoria/nuevo', 'ADMIN'),
('/categoria/guardar', 'ADMIN'),
('/categoria/modificar/** ', 'ADMIN'),
('/categoria/eliminar/**', 'ADMIN'),
('/usuario/nuevo', 'ADMIN'),
('/usuario/guardar', 'ADMIN'),
('/usuario/modificar/** ', 'ADMIN'),
('/usuario/eliminar/**', 'ADMIN'),
('/reportes/**', 'ADMIN'),
('/producto/listado', 'VENDEDOR'),
('/categoria/listado', 'VENDEDOR'),
('/usuario/listado', 'VENDEDOR'),
('/facturar/carrito', 'USER');

CREATE TABLE request_matcher_all (
    id_request_matcher INT AUTO_INCREMENT NOT NULL,
    pattern VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_request_matcher))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO request_matcher_all (pattern) VALUES ('/'),
('/index'),
('/errores/**'),
('/carrito/**'),
('/pruebas/**'),
('/reportes/**'),
('/registro/**'),
('/js/**'),
('/webjars/**');