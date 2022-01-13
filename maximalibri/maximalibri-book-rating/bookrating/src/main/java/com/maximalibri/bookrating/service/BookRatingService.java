package com.maximalibri.bookrating.service;

import com.maximalibri.bookrating.dto.BookAndAvgRating;
import com.maximalibri.bookrating.infrastructure.BookRatingRepository;
import com.maximalibri.bookrating.model.BookRating;
import com.maximalibri.bookrating.model.BookRatingId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookRatingService {
    private final BookRatingRepository bookRatingRepository;

    public BookRatingService(BookRatingRepository bookRatingRepository) {
        this.bookRatingRepository = bookRatingRepository;
    }

    public List<BookAndAvgRating> getTop12Books() {
        return this.bookRatingRepository.getTop12().stream().map(BookAndAvgRating::new).collect(Collectors.toList());
    }

    /** salveaza un vot al unei carti in baza de date, dat de un user indentificat prin id */
    public void saveBookRating(Integer userId, String isbn, Integer rating) {
        BookRating bookRating = new BookRating(new BookRatingId(userId, isbn),rating);
        bookRatingRepository.save(bookRating);
    }

    public void deleteBookRating(Integer userId, String isbn) {
        bookRatingRepository.deleteById(new BookRatingId(userId, isbn));
    }

    /** intoarce votul pe care un user identificat prin id l-a dat unei carti, indentificate prin isbn */
    public BookRating getBookRatingByUserAndIsbn(String isbn, Integer userId) {
        BookRatingId bookRatingId = new BookRatingId(userId, isbn);
        Optional<BookRating> optionalBookRating = bookRatingRepository.findById(bookRatingId);
        return optionalBookRating.orElse(null);
    }
}
