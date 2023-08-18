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
    private String introduction;
    private int heartCount;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.introduction = book.getIntroduction();
        this.heartCount = book.getHeartCount();
    }
}
