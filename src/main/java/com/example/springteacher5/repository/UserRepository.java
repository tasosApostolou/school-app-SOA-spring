package com.example.springteacher5.repository;

import com.example.springteacher5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameStartingWith(String username);
    User findUsersById(Long id);
}
