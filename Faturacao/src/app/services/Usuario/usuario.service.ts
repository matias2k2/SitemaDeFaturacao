import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuarios } from 'src/app/Model/Usuarios';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private apiUrl = `${environment.ApiUrl}`;

  constructor(private http: HttpClient) {}

  UsuariofinAll(): Observable<Usuarios[]> {
    return this.http.get<Usuarios[]>(`${this.apiUrl}/usuario`);
  }
  getByNome(nome: string): Observable<Usuarios[]> {
    return this.http.get<Usuarios[]>(`${this.apiUrl}/usuario/nome/${nome}`);
  }

  adicionarUsuario(obj: Usuarios): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/usuario`, obj);
  }
}
