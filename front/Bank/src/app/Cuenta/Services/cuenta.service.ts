import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cuenta } from '../Models/Cuenta';
import { Respuesta } from '../../Shared/Respuesta';

@Injectable({
  providedIn: 'root'
})
export class CuentaService {
  private apiServerUrl= environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getallCuentas(id_usuario: number): Observable<Respuesta<Cuenta[]>> {
    return this.http.get<Respuesta<Cuenta[]>>(`${this.apiServerUrl}/cuenta/cliente/${id_usuario}/cuentas`); 
  }
  public getCuentabyId(cuentaId: number): Observable<Respuesta<Cuenta>>{
    return this.http.get<Respuesta<Cuenta>>(`${this.apiServerUrl}/${cuentaId}`); 
  }

  public addCuenta(cuenta:Cuenta, idUser:number): Observable<Respuesta<Cuenta>> {
    return this.http.post<Respuesta<Cuenta>>(`${this.apiServerUrl}/cuenta/${idUser}`, cuenta);
  }
  public updateCuenta(cuenta: Cuenta): Observable<Respuesta<Cuenta>> {
    return this.http.put<Respuesta<Cuenta>>(`${this.apiServerUrl}/cuenta/update`, cuenta);
  }
  public inactiveCuenta(cuenta: Cuenta, idCuenta:number): Observable<Respuesta<Cuenta>> {
    return this.http.put<Respuesta<Cuenta>>(`${this.apiServerUrl}/cuenta/${idCuenta}/inactive`, cuenta);
  }
  public activeCuenta(cuenta: Cuenta, idCuenta:number): Observable<Respuesta<Cuenta>> {
    return this.http.put<Respuesta<Cuenta>>(`${this.apiServerUrl}/cuenta/${idCuenta}/active`, cuenta);
  }
  public deleteCuenta(cuentaId: number): Observable<Respuesta<number>> {
    return this.http.delete<Respuesta<number>>(`${this.apiServerUrl}/cuenta/delete/${cuentaId}`);
  }
}