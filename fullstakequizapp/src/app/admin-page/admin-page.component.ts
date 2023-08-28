import { Component, OnInit } from '@angular/core';
import { Answer } from '../interface/answer';
import { Question } from '../interface/question';
import { Topic } from '../interface/topic';
import { AnswerService } from '../service/answer.service';
import { QuestionService } from '../service/question.service';
import { TopicService } from '../service/topic.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.scss']
})
export class AdminPageComponent implements OnInit {

  public topicList: any = [];
  public selectedTopic!: Topic;
  // Array di risposte sbagliate;
  public answers: Answer[] = []; // Usare l'array di tipo Answer
  // Domanda
  public question: string = "";
  public questionId: number = this.questionService.getQuestions.length + 1;


  constructor(
    private topicService: TopicService,
    private questionService: QuestionService,
    private answerService: AnswerService) { }

  ngOnInit(): void {
    this.getAllTopics();
  }

  //* GET TOPICs
  getAllTopics() {
    this.topicService.getTopics()
      .subscribe(res => {
        this.topicList = res;
        console.log(this.topicList);
      })
  }

  // Aggiunge contenitore <div> di ANSWER FALSE
  createWrongAns() {
    // Creare un oggetto Answer con i valori di default
    this.answers.push({
      id: this.answerService.addAnswerList.length + 1,
      esito: false,
      risposta: '',
      quizEntity: this.questionId // Assegna l'ID della domanda
    });
  }


  inviaForm(): void {

    const questionObj: Question = {
      id: this.questionId,
      domanda: this.question,
      topicEntity: {
        id: 1, // TODO settare il TOPIC di appartenenza in base alla select
        topicTitle: '',
        quizEntity: undefined
      },
      risposte: []
    };

    this.questionService.addQuestion(questionObj)
      .subscribe(res => {
        console.log("Domanda aggiunta:", res);

        // const idComune = res.id;
        // questionObj.id = idComune;

        const risposteArray: Answer[] = this.answers.map(answer => {
          return {
            id: 0,
            esito: false, //TODO settare il boolean in base al tipo di contenitore riempito
            risposta: answer.risposta,
            quizEntity: questionObj.id // Assegna l'ID della domanda
          };
        });


        this.answerService.addAnswerList(risposteArray)
          .subscribe(response => {
            console.log("Risposte aggiunte:", response);
          });
      });
  }
}
