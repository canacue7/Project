import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Operaciones } from '../Models/Operaciones';
import { Respuesta } from '../../Shared/Respuesta';

@Injectable({
  providedIn: 'root'
})
export class OperacionesService {
  private apiServerUrl= environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getAllOps(id_cuenta: number): Observable<Respuesta<Operaciones[]>> {
    return this.http.get<Respuesta<Operaciones[]>>(`${this.apiServerUrl}/operaciones/${id_cuenta}`); 
  }
  public retirSaldo(id_cuenta: number, operaciones:Operaciones): Observable<Respuesta<Operaciones>> {
    return this.http.post<Respuesta<Operaciones>>(`${this.apiServerUrl}/operaciones/${id_cuenta}/withdraw`, operaciones); 
  }
  public addSaldo(id_cuenta: number, ops:Operaciones): Observable<Respuesta<Operaciones>> {
    return this.http.post<Respuesta<Operaciones>>(`${this.apiServerUrl}/operaciones/${id_cuenta}/add`, ops); 
  }
  public trSaldo(id_cuenta: number, id_cuentaD:number, ops:Operaciones): Observable<Respuesta<Operaciones>> {
    return this.http.post<Respuesta<Operaciones>>(`${this.apiServerUrl}/operaciones/${id_cuenta}/transfer/${id_cuentaD}`,ops); 
  }

}
