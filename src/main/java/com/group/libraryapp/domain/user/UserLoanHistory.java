package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_loan_history_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    private String bookName;
    private boolean isReturn;

    protected UserLoanHistory() {}

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public String getBookName() {
        return bookName;
    }

    public boolean getIsReturn() {
        return isReturn;
    }

    public void returnBook() {
        this.isReturn = true;
    }
}