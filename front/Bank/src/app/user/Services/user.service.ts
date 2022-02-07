import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Respuesta } from 'src/app/Shared/Respuesta';
import { environment } from 'src/environments/environment';
import { User } from '../Models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServerUrl= environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public login(user: User): Observable<Respuesta<User>> {
    console.log(user);
    return this.http.post<Respuesta<User>>(`${this.apiServerUrl}/users/auth`, user);
  }
  public get(): Observable<Respuesta<User[]>> {
    return this.http.get<Respuesta<User[]>>(`${this.apiServerUrl}/user`); 
  }
  public save(user: User): Observable<Respuesta<User>> {
    console.log(user);
    return this.http.post<Respuesta<User>>(`${this.apiServerUrl}/users`, user);
  }
  public delete(userName: String): Observable<Respuesta<number>> {
    return this.http.delete<Respuesta<number>>(`${this.apiServerUrl}/user/${userName}`);
  }
}
