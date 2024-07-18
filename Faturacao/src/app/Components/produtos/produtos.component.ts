import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
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
export class ProdutosComponent implements OnInit {
  isDivVisible: boolean = true;
  produtoForm!: FormGroup;
  categorias: Categoria[] = [];
  marcas: Marcas[] = [];
  produtos: Produtos[] = [];

  constructor(
    private fb: FormBuilder,
    private marcasServicos: MarcasService,
    private toastr: ToastrService,
    private servicoCategoria: CategoriaService,
    private serviceProduto: ProdutosService
  ) {
    this.produtoForm = this.fb.group({
      nomeProduto: ['', Validators.required],
      marcaId: ['', Validators.required],
      preco: ['', [Validators.required, this.validatePositiveNumber]],
      categoriaId: ['', Validators.required],
      quantidade: ['', [Validators.required, this.validatePositiveNumber]],
    });
  }

  ngOnInit(): void {
    this.marcaFindAll();
    this.categoriaFindAll();
  }

  marcaFindAll(): void {
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

  categoriaFindAll(): void {
    this.servicoCategoria.findAll().subscribe(
      (dados) => {
        this.categorias = dados;
        console.log('Categoria:', this.categorias);
      },
      (error) => {
        console.error('Erro ao buscar categoria', error);
      }
    );
  }

  onSubmitProdutos(): void {
    if (this.produtoForm.valid) {
      const produtoData: Produtos = {
        id: 0,
        nomeProduto: this.produtoForm.value.nomeProduto,
        marcaId: this.produtoForm.value.marcaId,
        preco: this.produtoForm.value.preco,
        categoriaId: this.produtoForm.value.categoriaId,
        quantidade: this.produtoForm.value.quantidade,
      };
      this.serviceProduto.adicionarProdutos(produtoData).subscribe(
        (response) => {
          this.toastr.success('Produto adicionado com sucesso');
          console.log('Produto adicionado com sucesso:', response);
          // Lógica adicional se necessário (por exemplo, resetar o formulário)
        },
        (error) => {
          this.toastr.error('Erro ao adicionar produto');
          console.error('Erro ao adicionar produto:', error);
        }
      );
    }
    console.log(this.produtoForm.value);
  }

  selecionarMarca(event: any): void {
    console.log(event.target.value);
  }

  voltar(): void {
    console.log('Voltando...');
  }

  private validatePositiveNumber(
    control: AbstractControl
  ): ValidationErrors | null {
    const value = control.value;
    if (value < 0) {
      return { negativeNumber: true };
    }
    return null;
  }
}
