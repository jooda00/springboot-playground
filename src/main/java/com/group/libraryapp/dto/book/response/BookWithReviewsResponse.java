package com.group.libraryapp.dto.book.response;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.review.Review;
import com.group.libraryapp.dto.review.response.ReviewResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class BookWithReviewsResponse {
    private Long id;
    private String name;
    private List<ReviewResponse> reviewResponses = new ArrayList<>();

    public BookWithReviewsResponse(Book book, List<ReviewResponse> reviewResponses) {
        this.id = book.getId();
        this.name = book.getName();
        this.reviewResponses = reviewResponses;
    }
}