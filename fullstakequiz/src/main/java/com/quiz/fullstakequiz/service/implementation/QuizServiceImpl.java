package com.quiz.fullstakequiz.service.implementation;

import com.quiz.fullstakequiz.model.Quiz;
import com.quiz.fullstakequiz.model.Topic;
import com.quiz.fullstakequiz.repo.QuizRepo;
import com.quiz.fullstakequiz.service.QuizService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@Slf4j // ci permette di vedere su CONSOLE cosa sta succedendo in uno specifico metodo (log)
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepo quizRepo;

    @Override
    public Quiz create(Quiz quiz) {
        log.info("Nuovo QUIZ salvato: {}", quiz.getDomanda());
        return quizRepo.save(quiz);
    }

    @Override
    public Collection<Quiz> list() {
        log.info("Recupero tutti i QUIZ...");
        return quizRepo.findAll();
    }

    @Override
    public Quiz get(Long id) {
        log.info("Quiz recuperato: {}", id);
        return quizRepo.findById(id).get();
    }

    @Override
    public Quiz update(Quiz quiz) {
        log.info("Quiz in aggiornamento: {}", quiz.getDomanda());
        return quizRepo.save(quiz);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("ID Quiz Eliminato: {}", id);
        quizRepo.deleteById(id);
        return Boolean.TRUE;
    }
}
