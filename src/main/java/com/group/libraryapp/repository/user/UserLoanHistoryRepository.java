package com.group.libraryapp.repository.user;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserLoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    UserLoanHistory findByBookName(String bookName);
    boolean existsByBookNameAndIsReturn(String bookName, boolean isReturn);
}
