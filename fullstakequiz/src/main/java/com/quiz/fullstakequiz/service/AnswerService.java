package com.quiz.fullstakequiz.service;

import com.quiz.fullstakequiz.model.Answer;
import com.quiz.fullstakequiz.model.Quiz;

import java.util.Collection;

public interface AnswerService {

    Answer create(Answer answer);

    Collection<Answer> list(Answer quizEntity);

    Answer get(Long id);

    Answer update(Answer answer);

    Boolean delete(Long id);
}