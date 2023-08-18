package com.group.libraryapp.controller.review;

import com.group.libraryapp.dto.review.request.ReviewRequest;
import com.group.libraryapp.service.review.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    private final Logger logger = LoggerFactory.getLogger(ReviewController.class);
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewRequest> saveReview(@RequestBody ReviewRequest reviewRequest) {
        logger.info("save review controller start");
        reviewService.saveReview(reviewRequest);
        return new ResponseEntity<>(reviewRequest, HttpStatus.CREATED);
    }

    @DeleteMapping("/review/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        logger.info("delete review controller start");
        reviewService.deleteReview(reviewId);
    }
}