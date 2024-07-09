import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-loginfrom',
  templateUrl: './loginfrom.component.html',
  styleUrls: ['./loginfrom.component.scss'],
})
export class LoginfromComponent {
  email: string = '';
  password: string = '';

  constructor(private toastr: ToastrService, private router: Router) {}

  onSubmit(): void {
    // Lógica de envio do formulário
    if (this.email === 'matias@gmail.com' && this.password === '1234') {
      this.toastr.success('Login successful!');
      console.log('Login successful:', {
        email: this.email,
        password: this.password,
      });
      this.router.navigate(['/admin']); // Redireciona para a rota 'inicio' após o login bem-sucedido
    } else {
      this.toastr.error('Invalid email or password');
      console.error('Login failed:', {
        email: this.email,
        password: this.password,
      });
    }
  }
}
