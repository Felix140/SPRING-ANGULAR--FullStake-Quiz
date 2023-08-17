package com.quiz.fullstakequiz.repo;

import com.quiz.fullstakequiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Long> {
    void deleteQuizById(Long id);
}
