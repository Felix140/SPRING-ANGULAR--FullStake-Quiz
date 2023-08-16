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

    @OneToOne
    @JoinColumn(name = "id_quiz")
    private Quiz quizEntity;


}
