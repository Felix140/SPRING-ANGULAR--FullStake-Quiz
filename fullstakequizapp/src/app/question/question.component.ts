import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../service/question.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit{

  public name: string="";
  public questionList: any= [];
  public currentQuestion: number = 0;
  // Score
  public points: number = 0;
  // Timer
  public timer: number = 0;
  // ProgressBar
  public progress: number = 10;
  

  constructor(private questionService: QuestionService) {}

  ngOnInit(): void {
    this.name = localStorage.getItem("name")!;
    this.getAllQuestions();
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
  }

  // DOMANDA PRECEDENTE
  previousQuestion() {
    this.currentQuestion--;
    this.progress -= 10;
  }

}
