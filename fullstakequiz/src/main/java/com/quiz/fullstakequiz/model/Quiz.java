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
public class Quiz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true, name = "id_quiz")
    private Long id;

    @Column(columnDefinition = "VARCHAR(500)")
    private String domanda;

    @ManyToOne
    @JoinColumn(name = "id_topic")
    private Topic topicEntity;

    @OneToMany(mappedBy = "quizEntity", cascade = CascadeType.ALL, orphanRemoval = true)//!
    private Set<Answer> risposte;

    //* SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public void setTopicEntity(Topic topicEntity) {
        this.topicEntity = topicEntity;
    }

    public void setRisposte(Set<Answer> risposte) {
        this.risposte = risposte;
    }
}
