package com.quiz.fullstakequiz.service;

import com.quiz.fullstakequiz.model.Answer;
import com.quiz.fullstakequiz.model.Quiz;
import com.quiz.fullstakequiz.model.Topic;

import java.util.Collection;

public interface QuizService {

    Quiz create(Quiz quiz);

    Collection<Quiz> list();

    Quiz get(Long id);

    Quiz update(Quiz quiz);

    Boolean delete(Long id);
}
