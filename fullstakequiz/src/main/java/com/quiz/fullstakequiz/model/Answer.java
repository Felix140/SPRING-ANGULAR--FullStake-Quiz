package com.quiz.fullstakequiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true, name = "id_answer")
    private Long id;

    private String risposta;

    private boolean esito;

    @ManyToOne
    @JoinColumn(name = "id_quiz")
    private Quiz quiz;

}
