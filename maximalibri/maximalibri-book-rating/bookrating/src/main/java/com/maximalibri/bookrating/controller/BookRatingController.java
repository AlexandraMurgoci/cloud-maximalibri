package com.maximalibri.bookrating.controller;

import com.maximalibri.bookrating.dto.BookAndAvgRating;
import com.maximalibri.bookrating.dto.BookRatingDto;
import com.maximalibri.bookrating.dto.PrimitiveWrapper;
import com.maximalibri.bookrating.model.BookRating;
import com.maximalibri.bookrating.service.BookRatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/book-rating")
public class BookRatingController {

    private final BookRatingService bookRatingService;

    public BookRatingController(BookRatingService bookRatingService) {
        this.bookRatingService = bookRatingService;
    }

    @GetMapping("/top")
    public List<BookAndAvgRating> getTopBooks() {
        return bookRatingService.getTop12Books();
    }

    //TODO
//    @PostMapping("/book-isbn-list")
//    public List<BookAndAvgRating> getBookIsbnAndRating(@RequestBody List<String> isbnList) {
//        bookRatingService.getBooksAndRatings(isbnList);
//    }

    @PostMapping("/rate")
    public PrimitiveWrapper<String> rateBook(@RequestBody BookRatingDto bookRatingDto) {
        this.bookRatingService.saveBookRating(bookRatingDto.userId, bookRatingDto.isbn, bookRatingDto.rating*2);
        return new PrimitiveWrapper<>("success");
    }

    @PostMapping("/un-rate")
    public PrimitiveWrapper<String> unRateBook(@RequestBody BookRatingDto bookRatingDto) {
        this.bookRatingService.deleteBookRating(bookRatingDto.userId, bookRatingDto.isbn);
        return new PrimitiveWrapper<>("success");
    }

    @GetMapping("/{isbn}/rating/user/{userId}")
    public PrimitiveWrapper<Float> getUserRating(@PathVariable("isbn") String isbn,
                                      @PathVariable("userId") Integer userId) {
        BookRating bookRating = this.bookRatingService.getBookRatingByUserAndIsbn(isbn, userId);
        return bookRating == null ?
                new PrimitiveWrapper<>(0f)
                : new PrimitiveWrapper<>(bookRating.getBookRating()/2f);
    }

}
