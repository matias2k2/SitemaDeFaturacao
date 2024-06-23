import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Marcas } from '../Model/Marcas';

@Injectable({
  providedIn: 'root'
})
export class MarcasService {

  private apiUrl = `${environment.ApiUrl}`;

  constructor(private http: HttpClient) {

  }

  MarcasfinAll():Observable<Marcas[]>{
      return this.http.get<Marcas[]>(`${this.apiUrl}/marcas`);
  }

  adicionarMarca(obj: Marcas):Observable<void>{
      return this.http.post<void>(`${this.apiUrl}/marcas`, obj);
  }
}
