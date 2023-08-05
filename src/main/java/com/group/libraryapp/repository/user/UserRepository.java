package com.group.libraryapp.repository.user;

import com.group.libraryapp.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
