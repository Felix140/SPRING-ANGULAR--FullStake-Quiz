package com.quiz.fullstakequiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true, name = "id_topic")
    private Long id;

    private String topicTitle;

    @OneToMany(mappedBy = "topicEntity")
    private Set<Quiz> quiz;

    //* SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public void setQuizEntity(Set<Quiz> quizEntity) {
        this.quiz = quizEntity;
    }
}
