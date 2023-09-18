package com.quiz.fullstakequiz.repo;

import com.quiz.fullstakequiz.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Long> {
    void deleteAdminById(Long id);
    Optional<Admin> findByAdminName(String login);
}
