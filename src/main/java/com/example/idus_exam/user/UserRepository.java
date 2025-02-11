package com.example.idus_exam.user;

import com.example.idus_exam.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Page<User> findAllByNameContains(Pageable pageable, String name);
    Page<User> findAllByEmailContains(Pageable pageable, String email);
}
