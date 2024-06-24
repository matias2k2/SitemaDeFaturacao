import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Marcas } from 'src/app/Model/Marcas';
import { MarcasService } from 'src/app/services/marcas.service';

@Component({
  selector: 'app-marcas',
  templateUrl: './marcas.component.html',
  styleUrls: ['./marcas.component.scss']
})
export class MarcasComponent {

  marcaForm!: FormGroup;
  constructor(private fb: FormBuilder, private marcasServicos: MarcasService) {

    this.marcaForm = this.fb.group({
      nome: ['', Validators.required]
    });
  }



  onSubmitMacacao(): void {
    if (this.marcaForm.valid) {
      const marcaData: Marcas = {
        nome: this.marcaForm.value.nome
      };
      this.marcasServicos.adicionarMarca(marcaData).subscribe(
        response => {
          console.log('Marca adicionada com sucesso:', response);
          // Lógica adicional se necessário (por exemplo, resetar o formulário)
        },
        error => {
          console.error('Erro ao adicionar marca:', error);
        }
      );
    }
  }
}




