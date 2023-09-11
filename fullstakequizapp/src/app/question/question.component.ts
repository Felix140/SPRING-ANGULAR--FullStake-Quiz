import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { shuffle } from 'lodash'; //! da installare (npm install lodash --save)
import { interval } from 'rxjs';
import { AnswerService } from '../service/answer.service';
import { QuestionService } from '../service/question.service';
import { TopicService } from '../service/topic.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit {

  //* QUESTION
  public name: string = "";
  public questionList: any = [];
  public currentQuestion: number = 0;
  // Score
  public points: number = 0;
  interval$: any;
  // Timer
  public timer: number = 60;
  // ProgressBar
  public progress: number = 10;

  //* ANSWER
  public answerListByQuiz: any = [];
  correctAnswer: number = 0;
  wrongAnswer: number = 0;

  //* RESULTS (modal)
  @ViewChild('resultButton') modalButton!: ElementRef;




  constructor(private questionService: QuestionService, private answerService: AnswerService, private topicService: TopicService) { }

  ngOnInit(): void {
    this.name = localStorage.getItem("name")!;
    this.getAllQuestions();
    this.getCurrentQuestionId();
    this.getAnswersByQuiz();
    this.startCounter();
  }

  //* GET ALL => questions
  getAllQuestions() {
    this.questionService.getQuestions()
      .subscribe(res => {
        console.log(res);
        //this.questionList = res;
        //this.questionList = shuffle(res).slice(0, 10); // MISCHIA in maniera RANDOMICA 10 questions
        const selectedTopic = this.topicService.getSelectedTopic(); //* DA QUI PRENDO IL TOPIC SELEZIONATO IN topic-config
        this.questionList = shuffle(res.filter((question: any) => //* FILTRA e poi:
          question.topicEntity.topicTitle === selectedTopic)).slice(0, 10); //* Seleziono le QUESTION inbase al SELECTEDTOPIC
      })
  }

  // PROSSIMA DOMANDA
  nextQuestion() {

    if (this.currentQuestion + 1 === 10) {
      this.stopCounter();
      // Clicca sul bottone del modal
      setTimeout(() => {
        this.modalButton.nativeElement.click();
      }, 1000);

    } else {
      // Metto un timeout prima di passare alla domanda successiva
      setTimeout(() => {
        this.currentQuestion++;
        this.progress += 10;
        this.getCurrentQuestionId();
        this.resetCounter();
      }, 1000);
    }

  }

  //* DOMANDA PRECEDENTE
  previousQuestion() {
    this.currentQuestion--;
    this.progress -= 10;
    this.getCurrentQuestionId();
  }

  //* RESETTA INTERO QUIZ
  resetQuiz() {
    this.resetCounter();
    this.getAllQuestions();
    this.points = 0;
    this.timer = 60;
    this.currentQuestion = 0;
    this.progress = 10;
    this.correctAnswer = 0;
    this.wrongAnswer = 0;

    // chiudi Modal
    document.getElementById("close-modal")?.click();
  }

  //* GET ALL ANSWERS(le filtro poi con ngIf)
  getAnswersByQuiz() {
    this.answerService.getAnswers()
      .subscribe(res => {
        console.log(res);
        this.answerListByQuiz = shuffle(res);
      });
  }

  //* ID della CURRENT QUESTION
  getCurrentQuestionId(): number {
    console.log(this.questionList[this.currentQuestion]?.id);
    return this.questionList[this.currentQuestion]?.id;
  }

  //* ESITO della Risposta
  selectAnswer(currentAnswer: boolean): void {

    if (currentAnswer) { //* se è corretta

      this.points += 10;
      this.correctAnswer++;
      this.nextQuestion();

    } else {  //* se è sbagliata

      if (this.points !== 0) {
        this.points -= 0;
      } else {
        this.points = 0;
      }
      this.wrongAnswer++;
      this.nextQuestion();
      
    }
  }


  //* TIMER
  startCounter() {

    this.interval$ = interval(1000)
      .subscribe(val => {
        this.timer--;
        if (this.timer === 0) {
          this.currentQuestion++;
          this.timer = 60;
        }
      });

    setTimeout(() => {
      this.interval$.unsubscribe();
    }, 600000); //dopo 10min stoppa il timer
  }

  stopCounter() {
    this.interval$.unsubscribe();
    this.timer = 0;
  }

  resetCounter() {
    this.stopCounter();
    this.timer = 60;
    this.startCounter();
  }
}
