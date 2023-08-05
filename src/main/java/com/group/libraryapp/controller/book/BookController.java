package com.group.libraryapp.controller.book;

import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.service.book.BookService;
import lombok.experimental.PackagePrivate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /* ResponseEntity 사용해서 dto및 상태코드 반환 코드로 리팩토링*/
    @PostMapping("/book")
    public ResponseEntity<BookCreateRequest> saveBook(@RequestBody BookCreateRequest request) {
        bookService.saveBook(request);
        return new ResponseEntity<BookCreateRequest>(request, HttpStatus.CREATED);
    }

    @PostMapping("/book/loan")
    public void loanBook(@RequestBody BookLoanRequest request) {
        bookService.loanBook(request);
    }

    @PutMapping("/book/return")
    public void returnBook(@RequestBody BookReturnRequest request) {
        bookService.returnBook(request);
    }
}