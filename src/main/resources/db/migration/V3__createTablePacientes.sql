CREATE TABLE IF NOT EXISTS medicos(
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    documentoIdentidad VARCHAR(14) NOT NULL UNIQUE,
    telefono varchar(7) NOT NULL UNIQUE ,
    --A continuación son los campos de la dirección que en este caso optamos por ponerlos como columnas
    calle VARCHAR(100) NOT NULL,
    distrito VARCHAR(100) NOT NULL,
    complemento VARCHAR(100) NOT NULL,
    numero VARCHAR(20),
    ciudad VARCHAR(100) NOT NULL
)