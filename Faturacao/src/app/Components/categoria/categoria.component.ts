import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria } from 'src/app/Model/Categoria';
import { Marcas } from 'src/app/Model/Marcas';
import { CategoriaService } from 'src/app/services/categoria.service';
import { MarcasService } from 'src/app/services/marcas.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.scss'],
})
export class CategoriaComponent {
  idFk?: number;
  marcaForm!: FormGroup;
  categoriaForm!: FormGroup;
  categorias: Categoria[] = [];
  marcas: Marcas[] = [];

  constructor(
    private servico: CategoriaService,
    private fb: FormBuilder,
    private marcasServicos: MarcasService,
    private toastr: ToastrService
  ) {
    this.marcaForm = this.fb.group({
      nome: ['', Validators.required],
    });

    this.categoriaForm = this.fb.group({
      nome: ['', Validators.required],
      marcaId: ['', Validators.required], // Se necessário, dependendo do seu modelo de dados
    });
  }

  onSubmit(): void {
    if (this.categoriaForm.valid) {
      const categoriaData: Categoria = {
        nome: this.categoriaForm.value.nome,
        marcaId: Number(this.categoriaForm.value.marcaId),
      };
      console.log(categoriaData);
      this.servico.addcategoria(categoriaData).subscribe(
        (response) => {
          this.toastr.success('Categoria adicionada com sucesso:');
          console.log('Categoria adicionada com sucesso:', response);
          // Lógica adicional se necessário (por exemplo, resetar o formulário)
        },
        (error) => {
          this.toastr.error('Erro ao adicionar categoria:');
          console.error('Erro ao adicionar categoria:', error);
        }
      );
    }
  }

  ngOnInit(): void {
    this.MarcaFindAll();
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
  onSubmitMacacao(): void {
    if (this.marcaForm.valid) {
      const marcaData: Marcas = {
        nome: this.marcaForm.value.nome,
      };
      this.marcasServicos.adicionarMarca(marcaData).subscribe(
        (response) => {
          console.log('Marca adicionada com sucesso:', response);

          // Lógica adicional se necessário (por exemplo, resetar o formulário)
        },
        (error) => {
          console.error('Erro ao adicionar marca:', error);
        }
      );
    }
  }
}

