package com.quiz.fullstakequiz.resource;


import com.quiz.fullstakequiz.model.Answer;
import com.quiz.fullstakequiz.model.Quiz;
import com.quiz.fullstakequiz.service.AnswerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerResource {

    private final AnswerService answerService;

    //* POST
    @PostMapping("/add")
    public ResponseEntity<Answer> addAnswer(@RequestBody Answer answer) {
        Answer newAnswer = answerService.create(answer);
        return new ResponseEntity<>(newAnswer, HttpStatus.CREATED);
    }


    //* GET (list)
    @GetMapping("/all")
    public ResponseEntity<Collection<Answer>> getAllAnswers() {
        Collection<Answer> allAnswers = answerService.list();
        return new ResponseEntity<>(allAnswers, HttpStatus.OK);
    }

    //* GET
    @GetMapping("/find/{id}")
    public ResponseEntity<Answer> getQuizById(@PathVariable("id") Long id) {
        Answer answer = answerService.get(id);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    //* PUT
    @PutMapping("/update")
    public ResponseEntity<Answer> updateQuiz(@RequestBody Answer answer) {
        Answer updateAnswer = answerService.update(answer);
        return new ResponseEntity<>(updateAnswer, HttpStatus.OK);
    }

    //* DELETE
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Answer> deleteAnswer(@PathVariable("id") Long id) {
        answerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //* DELETE (BY QUIZ)
    @Transactional
    @DeleteMapping("/delete_group/{id}")
    public void deleteAnswerByQuiz(@PathVariable("id") Long quizId) {
        answerService.deleteGroupByQuizId(quizId);
        new ResponseEntity<>(HttpStatus.OK);
    }
}
