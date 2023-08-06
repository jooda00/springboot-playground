package com.group.libraryapp.controller.review;

import com.group.libraryapp.dto.review.request.ReviewRequest;
import com.group.libraryapp.service.review.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewRequest> saveReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.saveReview(reviewRequest);
        return new ResponseEntity<>(reviewRequest, HttpStatus.CREATED);
    }

    @DeleteMapping("/review/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}