import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Categoria } from 'src/app/Model/Categoria';
import { CategoriaService } from 'src/app/services/categoria.service';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  userName: String = '';
  //Variavel para visiblidade
  categorias: Categoria[] = [];
  catigoriaOBJ = new Categoria();



  constructor(private servico: CategoriaService, private fb: FormBuilder) {

  }
  //Variavel Para Exibir ou mostra uma tela
  exibir: boolean = false;
  variavel: String = '';


  //Funcao para Exivir o Quadrado
  accaoSerRealizado(texto: string, event?: Event) {
    if (event) {
      event.preventDefault();
    }

    this.exibir = !this.exibir;
  }
  findAll(): void {
    this.servico.findAll().subscribe(
      retorno => this.categorias = retorno,
      error => console.error(error)
    );
  }
  addcategoria(): void {
    this.servico.addCategoria(this.catigoriaOBJ).subscribe(
      retorno => {
        this.categorias.push(retorno);
        this.catigoriaOBJ = new Categoria(); // Limpar o formulário após adicionar
      },
      error => console.error(error)
    );
  }


}
