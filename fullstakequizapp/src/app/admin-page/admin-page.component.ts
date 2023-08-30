import { Component, OnInit } from '@angular/core';
import { Answer } from '../interface/answer';
import { Question } from '../interface/question';
import { AnswerService } from '../service/answer.service';
import { QuestionService } from '../service/question.service';
import { TopicService } from '../service/topic.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.scss']
})
export class AdminPageComponent implements OnInit {

  //* TOPIC
  public topicList: any = [];
  public selectedTopic: string = "";
  public currTopicId!: number;
  public selectedButtonIndex: number | null = null;

  //* QUESTIONS
  public question!: string;
  public questionList: any = [];
  //CREO l'oggetto della QUESTION
  public questionObj: Question = {
    id: this.getIdQuestion(),
    domanda: '',
    topicEntity: {
      id: null
    },
    risposte: []
  };

  //* RISPOSTE
  public correctAnswer: Answer = {
    esito: true,
    risposta: '',
    quizEntity: {
      id: this.questionObj.id
    }
  };

  public unCorrectAnswer: Answer[] = [];



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
  //* SELECT TOPIC
  selectTopic(topicTitle: string, topicId: number, index: number): void {
    // topic TITLE
    this.topicService.setSelectedTopic(topicTitle);
    this.selectedTopic = topicTitle;
    this.selectedButtonIndex = index;
    console.log(this.selectedTopic);

    // topic ID
    this.currTopicId = topicId;
    console.log(this.currTopicId);
  }

  //* GET QUESTIONs + ASSIGN ID QUESTION
  getIdQuestion(): number {
    this.questionService.getQuestions()
      .subscribe(res => {
        this.questionList = res;
        console.log(this.questionList);
      })

    return this.questionList.length + 1; //qui assegno attributo ID di QUESTION
  }
  setQuestion(): Question {
    //* 1 -OVERRIDO e AGGIORNO l'oggetto questionObj
    this.questionObj = {
      id: this.getIdQuestion(),
      domanda: this.question,
      topicEntity: {
        id: this.currTopicId
      },
      risposte: []
    }

    return this.questionObj
  }

  //* Aggiunge contenitore <div> di ANSWER FALSE
  addWrongAns() {
    // Creare un oggetto Answer con i valori di default
    this.unCorrectAnswer.push({
      esito: false,
      risposta: '',
      quizEntity: {
        id: this.getIdQuestion()
      }
    });
  }
  setAnswers(): Answer[] {
    //* 1 -OVERRIDO e AGGIORNO l'oggetto questionObj
    this.questionObj = {
      id: this.getIdQuestion(),
      domanda: this.question,
      topicEntity: {
        id: this.currTopicId
      },
      risposte: []
    };

    //* 2 -CREO l'oggetto correctAnswer con l'ID del quiz corrente
    this.correctAnswer = {
      esito: true,
      risposta: this.correctAnswer.risposta,
      quizEntity: {
        id: this.questionObj.id
      }
    };

    //* 3 -UNISCO GLI ARRAY DI ANSWERS
    const allAnswers: Answer[] = [this.correctAnswer, ...this.unCorrectAnswer];
    return allAnswers;
  }



  //* INVIA FORM
  inviaForm(): void {

    const allAnswers: Answer[] = this.setAnswers();

    //? aggiungi QUESTION

    //* 3 -INVOCA metodo della richiesta HTTP per aggiungere la domanda
    this.questionService.addQuestion(this.setQuestion())
      .subscribe(res => {

        console.log("Domanda aggiunta:", res);
        console.log("Risposte da aggiungere:", allAnswers);

        //? Dopo aver aggiunto la domanda, invia le risposte

        //* 4 -INVOCA metodo della richiesta HTTP per aggiungere l'array di risposte
        this.answerService.addAnswerList(allAnswers)
          .subscribe(response => {
            console.log("Risposte aggiunte:", response);
          });
      });

  }
}
