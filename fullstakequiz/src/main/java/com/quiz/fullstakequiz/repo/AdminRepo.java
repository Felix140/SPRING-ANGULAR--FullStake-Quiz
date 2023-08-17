package com.quiz.fullstakequiz.repo;

import com.quiz.fullstakequiz.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Long> {
    void deleteAdminById(Long id);
}
