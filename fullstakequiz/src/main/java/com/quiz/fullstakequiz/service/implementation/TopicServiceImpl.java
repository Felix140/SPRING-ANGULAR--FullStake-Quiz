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
    public Collection<Topic> list() {
        log.info("Recupero tutti i TOPIC");
        return topicRepo.findAll();
    }

    @Override
    public Topic get(Long id) {
        log.info("TOPIC recuperato: {}", id);
        return topicRepo.findById(id).get(); //!
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
