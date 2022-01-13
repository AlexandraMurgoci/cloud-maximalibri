package com.maximalibri.bookreview.service;

import com.maximalibri.bookreview.dto.BookReview;
import com.maximalibri.bookreview.infrastructure.ReviewRepository;
import com.maximalibri.bookreview.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final GetReviewUsernameService getReviewUsernameService;
    private final GetReviewRatingService getReviewRatingService;

    public ReviewService(ReviewRepository reviewRepository,
                         GetReviewUsernameService getReviewUsernameService,
                         GetReviewRatingService getReviewRatingService) {
        this.reviewRepository = reviewRepository;
        this.getReviewUsernameService = getReviewUsernameService;
        this.getReviewRatingService = getReviewRatingService;
    }

    public List<BookReview> getReviewsForBook(String isbn) {
        List<Review> reviews = reviewRepository.findByIsbn(isbn);
        return reviews.stream().map(review -> new BookReview(
                getReviewUsernameService.get(review.getUserId()),
                getReviewRatingService.get(review.getUserId(), review.getIsbn()),
                review.getText()
        )).collect(Collectors.toList());
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }
}
