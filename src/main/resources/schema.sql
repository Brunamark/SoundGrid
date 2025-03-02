CREATE TABLE IF NOT EXISTS music(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    artist TEXT NOT NULL,
    album TEXT NOT NULL,
    genre TEXT NOT NULL,
    release_date DATE,
    duration INTEGER,           
    file_path TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS genre (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

INSERT OR IGNORE INTO genre (name) VALUES 
    ('POP'), 
    ('ROCK'), 
    ('HIP_HOP_RAP'), 
    ('JAZZ'), 
    ('ELETRONICA'), 
    ('CLASSICA'), 
    ('BLUES'), 
    ('COUNTRY'), 
    ('REGGAE'), 
    ('R_B'), 
    ('FUNK'), 
    ('METAL'), 
    ('SOUL'), 
    ('FOLK'), 
    ('SERTANEJO'), 
    ('SAMBA'), 
    ('FORRO'), 
    ('BOSSA_NOVA');