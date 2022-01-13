CREATE TABLE bx_book_ratings (
    user_id INTEGER NOT NULL,
    isbn VARCHAR(15) NOT NULL,
    book_rating INTEGER NOT NULL,
    PRIMARY KEY (user_id, isbn)
);