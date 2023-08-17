package com.group.libraryapp.service.heart;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.heart.Heart;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.heart.request.HeartRequest;
import com.group.libraryapp.repository.book.BookRepository;
import com.group.libraryapp.repository.heart.HeartRepository;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HeartService {
    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public HeartService(HeartRepository heartRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.heartRepository = heartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void saveHeart(HeartRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> {throw new IllegalArgumentException("유저가 없습니다.");}
        );
        Book book = bookRepository.findById(request.getBookId()).orElseThrow(
                () -> {throw new IllegalArgumentException("해당 책이 존재하지 않습니다.");}
        );
//        heartRepository.findByUserAndBook(user, book).ifPresent(
//                none -> {throw new IllegalArgumentException("해당 책에 이미 좋아요를 눌렀습니다.");}
//        );
        Heart heart = heartRepository.findByUserAndBook(user, book);
        if (heart != null && heart.isHeart()) {
            throw new IllegalArgumentException("해당 책에 이미 좋아요를 눌렀습니다.");
        }
        // 좋아요 누르고 -> 취소한 상태이면
        else if (heart != null && !heart.isHeart()) {
            heart.pushHeart();
            return;
        }
        heartRepository.save(new Heart(user, book));
    }

    @Transactional
    public void deleteHeart(HeartRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> {throw new IllegalArgumentException("유저가 없습니다.");}
        );
        Book book = bookRepository.findById(request.getBookId()).orElseThrow(
                () -> {throw new IllegalArgumentException("해당 책이 존재하지 않습니다.");}
        );
        Heart heart = heartRepository.findByUserAndBook(user, book);
        heart.returnHeart();
    }
}