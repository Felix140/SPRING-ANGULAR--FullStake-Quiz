package com.quiz.fullstakequiz.resource;


import com.quiz.fullstakequiz.model.Topic;
import com.quiz.fullstakequiz.service.TopicService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicResource {

    private final TopicService topicService;

    //* POST
    @PostMapping("/add")
    public ResponseEntity<Topic> addTopic(@RequestBody Topic topic) {
        Topic newTopic = topicService.create(topic);
        return new ResponseEntity<>(newTopic, HttpStatus.CREATED);
    }

    //* GET (list)
    @GetMapping("/all")
    public ResponseEntity<Collection<Topic>> getAllTopics() {
        Collection<Topic> allTopic = topicService.list();
        return new ResponseEntity<>(allTopic, HttpStatus.OK);
    }

    //* GET
    @GetMapping("/find/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable("id") Long id) {
        Topic topic = topicService.get(id);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    //* PUT
    @PutMapping("/update")
    public ResponseEntity<Topic> updateQuiz(@RequestBody Topic topic) {
        Topic updateTopic = topicService.update(topic);
        return new ResponseEntity<>(updateTopic, HttpStatus.OK);
    }

    //* DELETE
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable("id") Long id) {
        topicService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
