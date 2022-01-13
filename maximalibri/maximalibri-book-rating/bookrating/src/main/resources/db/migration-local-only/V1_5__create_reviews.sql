CREATE SEQUENCE sequence_reviews START 1;

CREATE TABLE reviews (
    review_id INTEGER CONSTRAINT reviews_pk PRIMARY KEY,
    user_id INTEGER NOT NULL,
    isbn VARCHAR(15) NOT NULL,
    text TEXT NOT NULL
);

ALTER SEQUENCE sequence_reviews OWNED BY reviews.review_id;