package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 예외 없으면 commit, 예외 있으면 rollback
    @Transactional
    public void saveUser(UserCreateRequest request) {
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    // 약간의 성능적 향상 존재
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        // User --> UserResponse로 변경 --> List로 반환하는 과정
        return users.stream().map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName()); // 객체 업데이트
        // 이거 안해줘도 '변경 감지'로 인해서 save 처리 됨
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name);
        if(user == null) {
            throw new IllegalArgumentException();
        }
        userRepository.delete(user);
    }
}
