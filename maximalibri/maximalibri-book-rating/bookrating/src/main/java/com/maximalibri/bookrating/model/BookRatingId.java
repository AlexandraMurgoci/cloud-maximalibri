package com.maximalibri.bookrating.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookRatingId implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column
    private String isbn;

    public BookRatingId() {
    }

    public BookRatingId(Integer userId, String isbn) {
        this.userId = userId;
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRatingId that = (BookRatingId) o;
        return userId.equals(that.userId) &&
                isbn.equals(that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, isbn);
    }

    public Integer getUserId() {
        return userId;
    }

    public String getIsbn() {
        return isbn;
    }
}
