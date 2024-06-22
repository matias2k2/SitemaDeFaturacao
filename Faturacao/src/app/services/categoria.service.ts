import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Categoria } from '../Model/Categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  private apiUrl = `${environment.ApiUrl}/categoria`;

  constructor(private http: HttpClient) {

  }
  addCategoria(obj : Categoria):Observable<Categoria>{
      return this.http.post<Categoria>(this.apiUrl,obj);
  }

  findAll(): Observable<Categoria[]>{
    return this.http.get<Categoria[]>(this.apiUrl);
  }
}
