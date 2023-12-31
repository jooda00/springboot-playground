package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.review.Review;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.dto.book.response.BookResponse;
import com.group.libraryapp.dto.book.response.BookWithReviewsResponse;
import com.group.libraryapp.dto.review.response.ReviewResponse;
import com.group.libraryapp.repository.book.BookRepository;
import com.group.libraryapp.repository.user.UserLoanHistoryRepository;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository
    ,UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName(), request.getIntroduction()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 책 찾고
        Book book = bookRepository.findByName(request.getBookName());
        if(book == null) {
            throw new IllegalArgumentException("대출할 책이 존재하지 않습니다.");
        }
        // 유저 찾고
        User user = userRepository.findByName(request.getUserName());
        if(user == null) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)){
            throw new IllegalArgumentException("이미 대출 중인 책입니다.");
        }
        user.loanBook(book.getName());
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        Book book = bookRepository.findByName(request.getBookName());
        if(book == null) {
            throw new IllegalArgumentException("대출할 책이 존재하지 않습니다.");
        }
        // 유저 찾고
        User user = userRepository.findByName(request.getUserName());
        if(user == null) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }
        // 이미 대출 중인 책인지 확인하고
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), true)) {
            throw new IllegalArgumentException("이미 반납된 책입니다.");
        }
        // 책 찾고, 반납
        user.returnBook(book.getName());
    }

    public List<BookResponse> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookResponse::new).collect(Collectors.toList());
    }

    public BookWithReviewsResponse getBookWithReviews(Long bookId) {
        // 책 찾기
        Book book = bookRepository.findBookWithReviewsById(bookId).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("책이 없습니다.");
                }
        );
        // 리뷰 찾기
        List<Review> reviews = book.getReviews();
        // 리뷰 dto 형식으로 바꾸기
        List<ReviewResponse> reviewResponses = reviews.stream().map(
                review -> {
                    return new ReviewResponse(review.getId(), review.getContent());
                }
        ).collect(Collectors.toList());
        // 책 반환해주기
        return new BookWithReviewsResponse(book, reviewResponses);
    }
}