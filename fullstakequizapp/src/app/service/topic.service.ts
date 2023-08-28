import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Topic } from '../interface/topic';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private readonly apiServerUrl = environment.apiBaseUrl;
  private selectedTopic: string = "";

  constructor(private http: HttpClient) { }

  //* POST
  public addAnswer(topic: Topic): Observable<Topic[]> {
    return this.http.post<Topic[]>(`${this.apiServerUrl}/topic/add`, topic);
  }


  //* GET (list)
  public getTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${this.apiServerUrl}/topic/all`);
  }


  //* GET
  public getTopicsByQuiz(topicId: number): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${this.apiServerUrl}/topic/find/${topicId}`);
  }


  //* PUT
  public updateTopics(topic: Topic): Observable<Topic[]> {
    return this.http.put<Topic[]>(`${this.apiServerUrl}/topic/update`, topic);
  }

  //* DELETE
  public deleteTopics(topicId: number): Observable<Topic[]> {
    return this.http.delete<Topic[]>(`${this.apiServerUrl}/topic/delete/${topicId}`);
  }



  //* GET e SET Topic SELEZIONATO -> topic-config.component.ts
  setSelectedTopic(topic: string) {
    this.selectedTopic = topic;
  }
  getSelectedTopic(): string {
    return this.selectedTopic;
  }
}
