import { Component, OnInit } from '@angular/core';
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
  // Timer
  public timer: number = 0;
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
  }

  //* GET ALL => questions
  getAllQuestions() {
    this.questionService.getQuestions()
      .subscribe(res => {
        console.log(res);
        this.questionList = res;
      })
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
}
