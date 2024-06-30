import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Fatura } from 'src/app/Model/Fatura';
import { environment } from 'src/environments/environment.development';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class FaturasService {
  private apiUrl = `${environment.ApiUrl}`;

  constructor(private http: HttpClient) {}

  FaturasfinAll(): Observable<Fatura[]> {
    return this.http.get<Fatura[]>(`${this.apiUrl}/fatura`);
  }
  getByNome(nome: string): Observable<Fatura[]> {
    return this.http.get<Fatura[]>(`${this.apiUrl}/fatura/nome/${nome}`);
  }

  adicionarFaturas(obj: Fatura): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/fatura`, obj);
  }
}
