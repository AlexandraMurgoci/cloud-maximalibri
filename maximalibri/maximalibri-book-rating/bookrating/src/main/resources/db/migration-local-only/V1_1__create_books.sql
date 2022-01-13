CREATE TABLE bx_books (
    isbn VARCHAR(15) CONSTRAINT books_pk PRIMARY KEY,
    book_title TEXT,
    book_author VARCHAR(150),
    year_of_publication INTEGER,
    publisher VARCHAR(150),
    image_url_s TEXT,
    image_url_m TEXT,
    image_url_l TEXT
);