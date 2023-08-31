package com.quiz.fullstakequiz.resource;

import com.quiz.fullstakequiz.model.Quiz;
import com.quiz.fullstakequiz.service.implementation.QuizServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizResource {

    private final QuizServiceImpl quizService;
    private final AnswerResource answerResource;


    //* POST
    @PostMapping("/add")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        Quiz newQuiz = quizService.create(quiz);
        return new ResponseEntity<>(newQuiz, HttpStatus.CREATED);
    }

    //* POST + Set ID
    @PostMapping("/add/{id}")
    public ResponseEntity<Quiz> addQuizById(@RequestBody Quiz quiz, @PathVariable("id") Long id) {
        Quiz newQuiz = quizService.create(quiz);
        return new ResponseEntity<>(newQuiz, HttpStatus.CREATED);
    }


    //* GET (list)
    @GetMapping("/all")
    public ResponseEntity<Collection<Quiz>> getAllQuiz() {
        Collection<Quiz> allQuiz = quizService.list();
        return new ResponseEntity<>(allQuiz, HttpStatus.OK);
    }

    //* GET
    @GetMapping("/find/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable("id") Long id) {
        Quiz quiz = quizService.get(id);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    //* PUT
    @PutMapping("/update")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        Quiz updateQuiz = quizService.update(quiz);
        return new ResponseEntity<>(updateQuiz, HttpStatus.OK);
    }

    //* DELETE
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Quiz> deleteQuiz(@PathVariable("id") Long id) {
        answerResource.deleteAnswerByQuiz(id); //* DELETE (BY QUIZ) = Elimina le risposte associate
        quizService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
