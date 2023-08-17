package com.quiz.fullstakequiz.service;

import com.quiz.fullstakequiz.model.Quiz;
import com.quiz.fullstakequiz.model.Topic;

import java.util.Collection;

public interface TopicService {

    Topic create(Topic topic);

    Collection<Topic> list();

    Topic get(Long id);

    Topic update(Topic topic);

    Boolean delete(Long id);
}
