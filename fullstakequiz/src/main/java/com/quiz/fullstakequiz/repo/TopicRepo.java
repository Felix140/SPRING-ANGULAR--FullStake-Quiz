package com.quiz.fullstakequiz.repo;

import com.quiz.fullstakequiz.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topic, Long> {

}
