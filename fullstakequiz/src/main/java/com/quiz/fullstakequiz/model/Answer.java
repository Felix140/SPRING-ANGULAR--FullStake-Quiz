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

    @Column(columnDefinition = "VARCHAR(500)")
    private String risposta;

    private boolean esito;

    @ManyToOne
    @JoinColumn(name = "id_quiz", nullable = false)
    private Quiz quizEntity;

    //* SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    public void setQuizEntity(Quiz quizEntity) {
        this.quizEntity = quizEntity;
    }
}
