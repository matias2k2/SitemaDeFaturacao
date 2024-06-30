import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria } from 'src/app/Model/Categoria';
import { Marcas } from 'src/app/Model/Marcas';

import { ToastrService } from 'ngx-toastr';
import { CategoriaService } from 'src/app/services/categoria.service';
import { MarcasService } from 'src/app/services/marcas.service';
import { Produtos } from 'src/app/Model/Produtos';
import { ProdutosService } from 'src/app/services/Produtos/produtos.service';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.scss'],
})
export class ProdutosComponent {
  isDivVisible: boolean = true; // Defina isDivVisible conforme necessário
  produtoForm!: FormGroup; // Declare o FormGroup para o formulário de produtos
  categorias: Categoria[] = [];
  marcas: Marcas[] = [];
  produtos: Produtos[] = [];

  constructor(
    private servico: CategoriaService,
    private fb: FormBuilder,
    private marcasServicos: MarcasService,
    private toastr: ToastrService,
    private servicoCatigoria: CategoriaService,
    private serviceProduto: ProdutosService
  ) {
    this.produtoForm = this.fb.group({
      nomeProduto: ['', Validators.required],
      marcaId: ['', Validators.required],
      preco: ['', Validators.required],
      categoriaId: ['', Validators.required],
    });
  }
  //Variavel Para Exibir ou mostra uma tela
  ngOnInit(): void {
    this.MarcaFindAll();
    this.CatigoriaFindAll();
  }
  MarcaFindAll(): void {
    this.marcasServicos.MarcasfinAll().subscribe(
      (dados) => {
        this.marcas = dados;

        console.log('Marcas:', this.marcas);
      },
      (error) => {
        console.error('Erro ao buscar marcas', error);
      }
    );
  }
  CatigoriaFindAll(): void {
    this.servicoCatigoria.findAll().subscribe(
      (dados) => {
        this.categorias = dados;
        console.log('Catigoria:', this.categorias);
      },
      (error) => {
        console.error('Erro ao buscar Catigoria', error);
      }
    );
  }

  onSubmitProdutos() {
    // Lógica para submeter o formulário de produtos
    if (this.produtoForm.valid) {
      const produtoData: Produtos = {
        nomeProduto: this.produtoForm.value.nomeProduto,
        marcaId: this.produtoForm.value.marcaId,
        preco: this.produtoForm.value.preco,
        categoriaId: this.produtoForm.value.categoriaId,
      };
      this.serviceProduto.adicionarProdutos(produtoData).subscribe(
        (response) => {
          this.toastr.success('Produtos adicionada com sucesso:');
          console.log('Categoria adicionada com sucesso:', response);
          // Lógica adicional se necessário (por exemplo, resetar o formulário)
        },
        (error) => {
          this.toastr.error('Erro ao adicionar Produtos:');
          console.error('Erro ao adicionar Produtos:', error);
        }
      );
    }

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
