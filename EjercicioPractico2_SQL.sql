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
    tipo VARCHAR(20) -- 'PELICULA' o 'OBRA'
);

-- Tabla de funciones
CREATE TABLE funciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pelicula INT,
    fecha DATE,
    hora TIME,
    sala VARCHAR(50),
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
INSERT INTO peliculas (titulo, tipo) VALUES
('El Rey León', 'OBRA'),
('Avengers: Endgame', 'PELICULA'),
('La Bella y la Bestia', 'OBRA'),
('Spider-Man: No Way Home', 'PELICULA');

-- Insertar funciones
INSERT INTO funciones (id_pelicula, fecha, hora, sala) VALUES
(1, '2025-04-20', '18:00:00', 'Sala A'),
(2, '2025-04-21', '20:00:00', 'Sala B'),
(3, '2025-04-22', '17:00:00', 'Sala C'),
(4, '2025-04-23', '21:00:00', 'Sala D');

-- Insertar reservas
INSERT INTO reservas (id_usuario, id_funcion, cantidad) VALUES
(1, 1, 2),
(2, 2, 1),
(1, 4, 3);