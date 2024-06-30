import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Clientes } from 'src/app/Model/Clientes';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class ClientesService {
  private apiUrl = `${environment.ApiUrl}`;

  constructor(private http: HttpClient) {}

  ClientesfinAll(): Observable<Clientes[]> {
    return this.http.get<Clientes[]>(`${this.apiUrl}/clientes`);
  }
  getByNome(nome: string): Observable<Clientes[]> {
    return this.http.get<Clientes[]>(`${this.apiUrl}/clientes/nome/${nome}`);
  }

  adicionarClientes(obj: Clientes): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/clientes`, obj);
  }
}
