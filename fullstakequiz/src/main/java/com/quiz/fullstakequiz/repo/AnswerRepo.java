package com.quiz.fullstakequiz.repo;

import com.quiz.fullstakequiz.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answer, Long> {
    void deleteAnswerById(Long id);
}
