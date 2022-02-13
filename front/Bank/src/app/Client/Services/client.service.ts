import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Client } from '../Models/Client';
import { Respuesta } from '../../Shared/Respuesta';


@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiServerUrl= environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getClients(): Observable<Respuesta<Client[]>> {
    return this.http.get<Respuesta<Client[]>>(`${this.apiServerUrl}/client`); 
  }
  public getClientById(clientId: number): Observable<Respuesta<Client>>{
    return this.http.get<Respuesta<Client>>(`${this.apiServerUrl}/client/${clientId}`); 
  }
  public addClient(client: Client): Observable<Respuesta<Client>> {
    console.log(client);
    return this.http.post<Respuesta<Client>>(`${this.apiServerUrl}/client`, client);
  }
  public updateClient(client: Client): Observable<Respuesta<Client>> {
    return this.http.put<Respuesta<Client>>(`${this.apiServerUrl}/client/update/`, client);
  }
  public deleteClient(clientId: number): Observable<Respuesta<number>> {
    return this.http.delete<Respuesta<number>>(`${this.apiServerUrl}/client/delete/${clientId}`);
  }
}