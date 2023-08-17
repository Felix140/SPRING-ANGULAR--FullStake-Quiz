package com.quiz.fullstakequiz.service.implementation;

import com.quiz.fullstakequiz.model.Answer;
import com.quiz.fullstakequiz.model.Quiz;
import com.quiz.fullstakequiz.repo.AnswerRepo;
import com.quiz.fullstakequiz.service.AnswerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@Slf4j // ci permette di vedere su CONSOLE cosa sta succedendo in uno specifico metodo (log)
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepo answerRepo;

    @Override
    public Answer create(Answer answer) {
        log.info("Nuova ANSWER salvata: {}", answer.getRisposta());
        return answerRepo.save(answer);
    }

    @Override
    public Collection<Answer> list() {
        log.info("Recupero tutte le ANSWERS del quiz");
        return answerRepo.findAll();
    }

    @Override
    public Answer get(Long id) {
        log.info("ANSWER ID recuperato: {}", id);
        return answerRepo.findById(id).get();
    }

    @Override
    public Answer update(Answer answer) {
        log.info("ANSWER in aggiornamento: {}", answer.getRisposta());
        return answerRepo.save(answer);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("ANSWER ID Elminato: {}", id);
        answerRepo.deleteById(id);
        return Boolean.TRUE;
    }
}
