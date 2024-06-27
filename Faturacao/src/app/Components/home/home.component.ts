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
  exibir: boolean = false;
  idFk?: number;
  variavel: String = '';
  isDivVisible: boolean = false;
  isMarca: boolean = false;
  isVenda: boolean = false;
  isCategoria: boolean = false;
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
  toggleDiv() {
    this.isDivVisible = !this.isDivVisible
  }

  //Funcao para Exivir o Quadrado
  accaoSerRealizado(texto: string, event?: Event) {
    if (event) {
      event.preventDefault();
    }

    if (texto === 'Cria Categorias') {
      this.isDivVisible = true;
      this.isVenda=false;
    } else if (texto === 'Resgistar venda') {

      this.isVenda = true;
    }
    this.isDivVisible = !this.isDivVisible;
  }


  Resgistarvenda(event: Event) {
    event.preventDefault();
    this.isVenda = !this.isVenda;
    
  }

  onSubmitMacacao(): void {
    if (this.marcaForm.valid) {
      const marcaData: Marcas = {
        nome: this.marcaForm.value.nome
      };
      this.marcasServicos.adicionarMarca(marcaData).subscribe(
        response => {
          console.log('Marca adicionada com sucesso:', response);
          this.isMarca = true;
          this.isCategoria = true;
          // Lógica adicional se necessário (por exemplo, resetar o formulário)
        },
        error => {
          console.error('Erro ao adicionar marca:', error);
        }
      );
    }
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
  selecionarMarca(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    this.idFk = +selectElement.value; // Já temos o id da marca selecionada aqui

  }

  onSubmit(): void {
    if (this.categoriaForm.valid) {
      const categoriaData: Categoria = {
        name: this.categoriaForm.value.name,
        marcas_id: this.idFk
      };
      this.servico.addcategoria(categoriaData).subscribe(

        response => {
          console.log(categoriaData)
          console.log('Categoria adicionada com sucesso:', response);
          // Lógica adicional se necessário (por exemplo, resetar o formulário)
        },
        error => {
          console.error('Erro ao adicionar categoria:', error);
        }
      );
    }
  }

  voltar():void{
    this.isDivVisible =!this.isDivVisible
  }


}
