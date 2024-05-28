CREATE TYPE gender_type AS ENUM ('MALE', 'FEMALE', 'OTHER');

CREATE TABLE members (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender gender_type,
    birth_date DATE,
    country VARCHAR(50),
    profile_image_url VARCHAR(255),
    biography TEXT,
    website VARCHAR(100),
    links TEXT,
    social_media_links TEXT,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);