import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Admin } from '../interface/admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private readonly apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  //* GET (list)
  public getAdmins(): Observable<Admin[]> {
    return this.http.get<Admin[]>(`${this.apiServerUrl}/admin/all`)
  }
}
