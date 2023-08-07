package com.group.libraryapp.dto.book.response;

import com.group.libraryapp.domain.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter

public class BookResponse {
    private Long id;
    private String name;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
    }
}
