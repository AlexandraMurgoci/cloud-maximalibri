package com.maximalibri.bookrating.dto;

import java.util.Objects;

public class BookAndAvgRating {
    private String isbn;
    private Float average;

    public BookAndAvgRating(IBookAndAvgRating bookAndAvgRating) {
        this.isbn = bookAndAvgRating.getIsbn();
        this.average = bookAndAvgRating.getAverage();
    }

    public BookAndAvgRating() {
    }

    public String getIsbn() {
        return isbn;
    }

    public Float getAverage() {
        return average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAndAvgRating that = (BookAndAvgRating) o;
        return isbn.equals(that.isbn) && Objects.equals(average, that.average);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, average);
    }
}
