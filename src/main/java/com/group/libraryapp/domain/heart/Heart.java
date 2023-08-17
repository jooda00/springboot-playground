package com.group.libraryapp.domain.heart;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "heart")
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean heart;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Heart(User user, Book book) {
        this.user = user;
        this.book = book;
        this.heart = true;
    }

    public void returnHeart() {
        this.heart = false;
    }

    public void pushHeart() {
        this.heart = true;
    }
}