package com.group.libraryapp.repository.heart;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.heart.Heart;
import com.group.libraryapp.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Heart findByUserAndBook(User user, Book book);
//    Optional<Heart> findByUserAndBook(User user, Book book);
}