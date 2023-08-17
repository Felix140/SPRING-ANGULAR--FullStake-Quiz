package com.quiz.fullstakequiz.repo;

import com.quiz.fullstakequiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    void deleteUserById(Long id);
}
