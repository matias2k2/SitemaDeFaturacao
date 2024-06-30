import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Fatura } from 'src/app/Model/Fatura';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class FaturasService {
  private apiUrl = `${environment.ApiUrl}`;

  constructor(private http: HttpClient) {}

  UsuariofinAll(): Observable<Fatura[]> {
    return this.http.get<Fatura[]>(`${this.apiUrl}/marcas`);
  }
  getByNome(nome: string): Observable<Fatura[]> {
    return this.http.get<Fatura[]>(`${this.apiUrl}/marcas/nome/${nome}`);
  }

  adicionarUsuario(obj: Fatura): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/marcas`, obj);
  }
}
