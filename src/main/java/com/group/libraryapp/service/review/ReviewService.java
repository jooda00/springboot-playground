package com.group.libraryapp.service.review;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.review.Review;
import com.group.libraryapp.dto.review.request.ReviewRequest;
import com.group.libraryapp.repository.book.BookRepository;
import com.group.libraryapp.repository.review.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void saveReview(ReviewRequest request) {
        // 이름으로 책 찾고
        Book book = bookRepository.findByName(request.getBookName());
        // 예외 던지고
        if(book == null) {
            throw new IllegalArgumentException("리뷰를 남길 책이 없습니다.");
        }
        // 저장
        reviewRepository.save(new Review(request.getContent(), book));
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("작성한 리뷰가 없습니다.");
                }
        );
        reviewRepository.delete(review);
    }
}