import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Usuarios } from 'src/app/Model/Usuarios';
import { UsuarioService } from 'src/app/services/Usuario/usuario.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sigin',
  templateUrl: './sigin.component.html',
  styleUrls: ['./sigin.component.scss'],
})
export class SiginComponent {

  usuario : Usuarios[] = [];
  usuarioForm !:FormGroup;
  constructor(private servicos: UsuarioService, private fb: FormBuilder,
    private router: Router,
    private toastr: ToastrService) {
        this.usuarioForm = this.fb.group({
          nome:['',Validators.required],
          email:['',Validators.required],
          senha:['',Validators.required],
          confirSenha:['',Validators.required]
        });
  }
  onSubmit():void{
    if(this.usuarioForm.valid){
      const usuarioData : Usuarios ={
        nome :this.usuarioForm.value.nome,
        email:this.usuarioForm.value.email,
        senha:this.usuarioForm.value.senha
      }
      if (usuarioData.senha === this.usuarioForm.value.confirSenha) {
        this.servicos.adicionarUsuario(usuarioData).subscribe(
          (response) => {
            this.toastr.success('Usuario adicionada com sucesso:');
            console.log('Usuario adicionada com sucesso:', response);
            // Lógica adicional se necessário (por exemplo, resetar o formulário)
             this.router.navigate(['/inicio']);
          },
          (error) => {
            this.toastr.error('Este nome Ja se encontra registado');
            console.error('Erro ao adicionar categoria:', error);
          }
        );
    }else{
       this.toastr.error('Senha difeerentes');

    }}
  }
}

