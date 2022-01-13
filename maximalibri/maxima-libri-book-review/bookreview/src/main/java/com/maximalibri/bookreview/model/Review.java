package com.maximalibri.bookreview.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "sequence_reviews", initialValue=1, allocationSize=1)
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_reviews")
    @Column(name = "review_id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "isbn")
    private String isbn;

    @Column
    private String text;

    public Review() {
    }

    public Review(Integer userId, String isbn) {
        this.userId = userId;
        this.isbn = isbn;
        this.text = "";
    }

    public Review(Integer userId, String isbn, String text) {
        this.userId = userId;
        this.isbn = isbn;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id.equals(review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", isbn='" + isbn + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
