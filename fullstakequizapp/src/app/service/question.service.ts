import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Question } from '../interface/question';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private readonly apiServerUrl = environment.apiBaseUrl;

  // facciamo qui sotto un INJECTION dell'HttpClient nel costruttore (Dependency Injection)
  constructor(private http: HttpClient) { }

  //* POST
  public addQuestion(question: Question): Observable<Question[]> {
    return this.http.post<Question[]>(`${this.apiServerUrl}/quiz/add`, question);
  }


  //* GET (list)
  public getQuestions(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.apiServerUrl}/quiz/all`); //! attenzione: vanno aggiunti i BACKTICK
  }


  //* PUT
  public updateQuestions(question: Question): Observable<Question[]> {
    return this.http.put<Question[]>(`${this.apiServerUrl}/quiz/update`, question);
  }

  //* DELETE
  public deleteQuestions(questionId: number): Observable<Question[]> {
    return this.http.delete<Question[]>(`${this.apiServerUrl}/quiz/delete/${questionId}`);
  }
}
