import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria } from 'src/app/Model/Categoria';
import { Marcas } from 'src/app/Model/Marcas';
import { CategoriaService } from 'src/app/services/categoria.service';
import { MarcasService } from 'src/app/services/marcas.service';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  userName: String = '';
  //Variavel para visiblidade
  categoriaForm!: FormGroup;
  marcaForm!: FormGroup;
  marcaRegistrada: boolean = false; // Variável para controlar se a marca foi registrada com sucesso
  categorias: Categoria[] = [];
  marcas: Marcas[] = [];
  marcaCarregada: boolean = false;

  catigoriaOBJ: Categoria =
    {
      name: '',

    }

  constructor(private servico: CategoriaService, private fb: FormBuilder, private marcasServicos: MarcasService) {
    this.categoriaForm = this.fb.group({
      name: ['', Validators.required],
      marcas_id: [null] // Se necessário, dependendo do seu modelo de dados
    });
    this.marcaForm = this.fb.group({
      nome: ['', Validators.required]
    });
    this.marcaCarregada = false;

  }
  //Variavel Para Exibir ou mostra uma tela

  ngOnInit(): void {
    this.MarcaFindAll();
  }
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

  MarcaFindAll(): void {
    this.marcasServicos.MarcasfinAll().subscribe(
      dados => {
        this.marcas = dados;

        console.log('Marcas:', this.marcas);
      },
      error => {
        console.error('Erro ao buscar marcas', error);
      }
    );
  }



  onSubmit(): void {
    if (this.categoriaForm.valid) {
      const categoriaData: Categoria = {
        name: this.categoriaForm.value.name,
        marcas_id: this.marcas.length // Use o valor selecionado do campo marcas_id
      };

      this.servico.addcategoria(categoriaData).subscribe(
        response => {
          console.log('Categoria adicionada com sucesso:', response);
          this.marcaRegistrada = true; // Marca como registrada com sucesso para mostrar mensagem
          // Lógica adicional se necessário (por exemplo, resetar o formulário)
          this.categoriaForm.reset();
        },
        error => {
          console.error('Erro ao adicionar categoria:', error);
        }
      );
    }
  }
  onSubmitMacacao(): void {
    const marcaData: Marcas = {
      nome: this.marcaForm.value.nome
    };
    this.marcaCarregada = true;
    this.marcasServicos.adicionarMarca(marcaData).subscribe(
      response => {
        console.log('Marca adicionada com sucesso:', response);
        this.marcaCarregada = true; // Atualizar para true após adição bem-sucedida
      },
      error => {
        console.error('Erro ao adicionar marca:', error);
      }

    );
  }

}
