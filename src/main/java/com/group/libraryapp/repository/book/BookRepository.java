package com.group.libraryapp.repository.book;

import com.group.libraryapp.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String bookName);
}