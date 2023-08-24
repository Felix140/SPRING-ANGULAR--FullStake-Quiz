import { Component, OnInit } from '@angular/core';
import { QuestionService } from '../service/question.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit{

  public name : string="";
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
    })
  }

}
