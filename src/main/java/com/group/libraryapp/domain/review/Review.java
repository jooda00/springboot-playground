package com.group.libraryapp.domain.review;

import com.group.libraryapp.domain.book.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Entity
@NoArgsConstructor // 기본 생성자
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Review(String content, Book book) {
        this.content = content;
        this.book = book;
    }
}
