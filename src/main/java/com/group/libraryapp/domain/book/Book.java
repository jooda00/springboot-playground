package com.group.libraryapp.domain.book;

import com.group.libraryapp.domain.review.Review;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String introduction;

    // 지연 로딩
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    protected Book() {}

    public Book(String name, String introduction) {
        this.name = name;
        this.introduction = introduction;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getIntroduction() { return this.introduction;}

}