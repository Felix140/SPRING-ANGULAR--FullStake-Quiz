import { Component, OnInit } from '@angular/core';
import { interval } from 'rxjs';
import { AnswerService } from '../service/answer.service';
import { QuestionService } from '../service/question.service';

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

  constructor(private questionService: QuestionService, private answerService: AnswerService) { }

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
        this.questionList = res;
      })
      // TODO Selezionare in maniera randomica 10 questions
  }

  // PROSSIMA DOMANDA
  nextQuestion() {
    this.currentQuestion++;
    this.progress += 10;
    this.getCurrentQuestionId();
  }

  // DOMANDA PRECEDENTE
  previousQuestion() {
    this.currentQuestion--;
    this.progress -= 10;
    this.getCurrentQuestionId();
  }
  
  //RESETTA INTERO QUIZ
  resetQuiz() {
    this.resetCounter();
    this.getAllQuestions();
    this.points = 0;
    this.timer = 60;
    this.currentQuestion = 0;
    this.progress = 10;
    //TODO Randomizzare le domande
  }

  //* GET ALL ANSWERS(le filtro poi con ngIf)
  getAnswersByQuiz() {
    this.answerService.getAnswers()
      .subscribe(res => {
        console.log(res);
        this.answerListByQuiz = res;
      });
  }

  //* ID della CURRENT QUESTION
  getCurrentQuestionId(): number {
    console.log(this.questionList[this.currentQuestion]?.id);
    return this.questionList[this.currentQuestion]?.id;
  }

  //* ESITO della Risposta
  selectAnswer(currentAnswer: boolean): void {

    if (currentAnswer) {
      this.points += 10;
      this.correctAnswer++;
    } else {
      if (this.points !== 0) {
        this.points -= 10
      } else {
        this.points = 0;
      }
      this.wrongAnswer++;
    }
  }


  //* TIMER
  startCounter() {
    
    this.interval$ = interval(1000)
    .subscribe(val=>{
      this.timer--;
      if(this.timer === 0) {
        this.currentQuestion++;
        this.timer = 60;
        this.points -= 10;
      }
    });
    
    setTimeout(()=>{
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
