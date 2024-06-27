import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Categoria } from 'src/app/Model/Categoria';
import { Marcas } from 'src/app/Model/Marcas';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent {
  isDivVisible: boolean = true; // Defina isDivVisible conforme necessário
  produtoForm!: FormGroup; // Declare o FormGroup para o formulário de produtos
  categorias: Categoria[] = [];
  marcas: Marcas[] = [];

  constructor(private formBuilder: FormBuilder) {

  }

  onSubmitProdutos() {
    // Lógica para submeter o formulário de produtos
    console.log(this.produtoForm.value); // Exemplo de log, ajuste conforme necessário
  }

  selecionarMarca(event: any) {
    // Lógica para selecionar a marca
    console.log(event.target.value); // Exemplo de log, ajuste conforme necessário
  }

  voltar() {
    // Lógica para voltar
    console.log('Voltando...'); // Exemplo de log, ajuste conforme necessário
  }

}
