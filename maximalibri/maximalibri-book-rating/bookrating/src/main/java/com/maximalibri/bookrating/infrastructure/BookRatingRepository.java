package com.maximalibri.bookrating.infrastructure;

import com.maximalibri.bookrating.dto.IBookAndAvgRating;
import com.maximalibri.bookrating.model.BookRating;
import com.maximalibri.bookrating.model.BookRatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRatingRepository extends JpaRepository<BookRating, BookRatingId> {
    /** get the 12 best books relative to popularity and average rating */
    @Query(value = "SELECT bx_book_ratings.isbn as isbn, ROUND((SUM(book_rating)+1000*((SELECT AVG(book_rating) as avgall from public.bx_book_ratings)))/(1000+COUNT(book_rating)),2) as average\n" +
            "FROM bx_book_ratings \n" +
            "GROUP BY isbn \n" +
            "ORDER BY average DESC\n" +
            "LIMIT 12;",
            nativeQuery = true)
    List<IBookAndAvgRating> getTop12();
}
