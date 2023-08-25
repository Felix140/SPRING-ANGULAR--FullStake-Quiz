import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Answer } from '../interface/answer';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  private readonly apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  //* POST
  public addAnswer(answer: Answer): Observable<Answer[]> {
    return this.http.post<Answer[]>(`${this.apiServerUrl}/answer/add`, answer);
  }


  //* GET (list)
  public getAnswers(): Observable<Answer[]> {
    return this.http.get<Answer[]>(`${this.apiServerUrl}/answer/all`);
  }


  //* GET (list by Quiz)
  public getAnswersByQuiz(quizId: number): Observable<Answer[]> {
    return this.http.get<Answer[]>(`${this.apiServerUrl}/answer/find_quiz/${quizId}`);
  }


  //* PUT
  public updateAnswers(question: Answer): Observable<Answer[]> {
    return this.http.put<Answer[]>(`${this.apiServerUrl}/answer/update`, question);
  }

  //* DELETE
  public deleteAnswers(answerId: number): Observable<Answer[]> {
    return this.http.delete<Answer[]>(`${this.apiServerUrl}/answer/delete/${answerId}`);
  }
}
