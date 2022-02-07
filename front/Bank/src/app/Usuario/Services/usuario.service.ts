import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Usuario } from '../Models/Usuario';
import { Respuesta } from '../../Shared/Respuesta';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiServerUrl= environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getUsuarios(): Observable<Respuesta<Usuario[]>> {
    return this.http.get<Respuesta<Usuario[]>>(`${this.apiServerUrl}/usuario`); 
  }
  public getUsuarioById(usuarioId: number): Observable<Respuesta<Usuario>>{
    return this.http.get<Respuesta<Usuario>>(`${this.apiServerUrl}/usuario/${usuarioId}`); 
  }
  public addUsuario(usuario: Usuario): Observable<Respuesta<Usuario>> {
    console.log(usuario);
    return this.http.post<Respuesta<Usuario>>(`${this.apiServerUrl}/usuario`, usuario);
  }
  public updateUsuario(usuario: Usuario): Observable<Respuesta<Usuario>> {
    return this.http.put<Respuesta<Usuario>>(`${this.apiServerUrl}/usuario/update`, usuario);
  }
  public deleteUsuario(usuarioId: number): Observable<Respuesta<number>> {
    return this.http.delete<Respuesta<number>>(`${this.apiServerUrl}/usuario/delete/${usuarioId}`);
  }
}