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

    private String domanda;

    @OneToOne(mappedBy = "quizEntity")
    private Topic topicEntity;

    @OneToMany(mappedBy = "quizEntity")
    private Set<Answer> risposte;

}
