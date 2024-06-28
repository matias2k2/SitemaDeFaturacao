import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria } from 'src/app/Model/Categoria';
import { Marcas } from 'src/app/Model/Marcas';
import { CategoriaService } from 'src/app/services/categoria.service';
import { MarcasService } from 'src/app/services/marcas.service';

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
    private marcasServicos: MarcasService
  ) {
    this.marcaForm = this.fb.group({
      nome: ['', Validators.required],
    });
  }

  onSubmit(): void {}
  selecionarMarca(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    this.idFk = +selectElement.value; // Já temos o id da marca selecionada aqui
  }
}

