import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produtos } from 'src/app/Model/Produtos';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class ProdutosService {
  private apiUrl = `${environment.ApiUrl}`;

  constructor(private http: HttpClient) {}

  ProdutosfinAll(): Observable<Produtos[]> {
    return this.http.get<Produtos[]>(`${this.apiUrl}/produtos`);
  }
  getByNome(nome: string): Observable<Produtos[]> {
    return this.http.get<Produtos[]>(`${this.apiUrl}/produtos/nome/${nome}`);
  }

  adicionarProdutos(obj: Produtos): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/produtos`, obj);
  }
}
