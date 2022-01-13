package com.maximalibri.bookreview.controller;

import com.maximalibri.bookreview.dto.BookReview;
import com.maximalibri.bookreview.dto.BookReviewDto;
import com.maximalibri.bookreview.dto.PrimitiveWrapper;
import com.maximalibri.bookreview.model.Review;
import com.maximalibri.bookreview.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://192.168.1.100:4200")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
@RequestMapping("/book-review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/{isbn}")
    public List<BookReview> getBookReviews(@PathVariable("isbn") String isbn) {
        return this.reviewService.getReviewsForBook(isbn);
    }

    @PostMapping("/user/review")
    public PrimitiveWrapper<String> reviewBook(@RequestBody BookReviewDto bookReviewDto) {
        this.reviewService.saveReview(new Review(bookReviewDto.userId, bookReviewDto.isbn, bookReviewDto.review));
        return new PrimitiveWrapper<String>("success");
    }

}
