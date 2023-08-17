package com.quiz.fullstakequiz.service.implementation;

import com.quiz.fullstakequiz.model.Quiz;
import com.quiz.fullstakequiz.model.Topic;
import com.quiz.fullstakequiz.repo.TopicRepo;
import com.quiz.fullstakequiz.service.TopicService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@Slf4j // ci permette di vedere su CONSOLE cosa sta succedendo in uno specifico metodo (log)
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepo topicRepo;

    @Override
    public Topic create(Topic topic) {
        log.info("Nuovo TOPIC salvato: {}", topic.getTopicTitle());
        return topicRepo.save(topic);
    }

    @Override
    public Collection<Topic> list(Quiz quiz) {
        log.info("Recupero tutti i TOPIC");
        return topicRepo.findAll();
    }

    @Override
    public String get(Topic topicTitle) {
        log.info("TOPIC recuperato: {}", topicTitle);
        return topicTitle.getTopicTitle(); //!
    }

    @Override
    public Topic update(Topic topic) {
        log.info("Topic in aggiornamento: {}", topic.getTopicTitle());
        return topicRepo.save(topic);
    }

    @Override
    public Boolean delete(Long id) {
        topicRepo.deleteById(id);
        return Boolean.TRUE;
    }
}
