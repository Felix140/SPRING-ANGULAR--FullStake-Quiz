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
  //* QUESTIONS
  public question!: string;
  public questionList: any = [];
  //CREO l'oggetto della QUESTION
  public questionObj: Question = {
    id: this.getIdQuestion(),
    domanda: this.question,
    topicEntity: {
      id: 1, // TODO settare il TOPIC di appartenenza in base alla select
      topicTitle: '',
      quizEntity: []
    },
    risposte: []
  };
  //* RISPOSTE
  public correctAnswer: Answer = {
    esito: true,
    risposta: '',
    quizEntity: { //? ATTENZIONE: bisogna creare un oggetto dentro l'interfaccia
      id: this.questionObj.id,

    }
  }
  public unCorrectAnswer: Answer[] = [{
    esito: false,
    risposta: '',
    quizEntity: { //? ATTENZIONE: bisogna creare un oggetto dentro l'interfaccia
      id: this.questionObj.id,
    }
  }
  ];


  constructor(
    private topicService: TopicService,
    private questionService: QuestionService,
    private answerService: AnswerService) { }

  ngOnInit(): void {
    this.getAllTopics();
    this.getIdQuestion();
  }

  //* GET TOPICs
  getAllTopics() {
    this.topicService.getTopics()
      .subscribe(res => {
        this.topicList = res;
        console.log(this.topicList);
      })
  }
  //* GET QUESTIONs + ASSIGN ID QUESTION
  getIdQuestion(): number {
    this.questionService.getQuestions()
      .subscribe(res => {
        this.questionList = res;
        console.log(this.questionList);
      })

    const questionId: number = this.questionList.length + 1; //qui assegno attributo ID di QUESTION
    return questionId
  }


  //* Aggiunge contenitore <div> di ANSWER FALSE
  addWrongAns() {
    // Creare un oggetto Answer con i valori di default
    this.unCorrectAnswer.push({
      esito: false,
      risposta: '',
      quizEntity: {
        id: this.questionObj.id,
      }
    });

  }

  inviaForm(): void {

    //* CREO l'array contenente TUTTE le ANSWERS
    const risposteArray: Answer[] = [this.correctAnswer, ...this.unCorrectAnswer];

    //? aggiungi QUESTION

    //* INVOCA metodo della richiesta HTTP per aggiungere la domanda
    this.questionService.addQuestion(this.questionObj)
      .subscribe(res => {
        console.log("Domanda aggiunta:", res);
        console.log("Risposte da aggiungere:", risposteArray);
        //? Dopo aver aggiunto la domanda, invia le risposte

        //* INVOCA metodo della richiesta HTTP per aggiungere le risposte
        this.answerService.addAnswerList(risposteArray)
          .subscribe(response => {
            console.log("Risposte aggiunte:", response);
          });
      });
      
  }

}
