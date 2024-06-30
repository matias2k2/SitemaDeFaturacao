import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Fatura } from 'src/app/Model/Fatura';
import { Produtos } from 'src/app/Model/Produtos';
import { Usuarios } from 'src/app/Model/Usuarios';
import { FaturasService } from 'src/app/services/Faturas/faturas.service';
import { ProdutosService } from 'src/app/services/Produtos/produtos.service';
import { UsuarioService } from 'src/app/services/Usuario/usuario.service';
import jsPDF from 'jspdf';
import { Clientes } from 'src/app/Model/Clientes';
import { ClientesService } from 'src/app/services/Clientes/clientes.service';

@Component({
  selector: 'app-fromulario',
  templateUrl: './fromulario.component.html',
  styleUrls: ['./fromulario.component.scss'],
})
export class FromularioComponent {
  clientes: Clientes[] = [];
  produtos: Produtos[] = [];
  faturasForm!: FormGroup;
  clienteForm!: FormGroup;
  constructor(
    private serverFaturas: FaturasService,
    private fb: FormBuilder,
    private clienteServico: ClientesService,
    private toastr: ToastrService,
    private produtosService: ProdutosService
  ) {
    this.faturasForm = this.fb.group({
      dataEmissao: ['', Validators.required],
      valorTotal: ['', Validators.required],
      quantidade: ['', Validators.required],
      produtoId: ['', Validators.required],
      clienteId: ['', Validators.required],
      categoriaId: ['', Validators.required],
      marcaId: ['', Validators.required],
      usuarioId: ['', Validators.required],
    });
    this.clienteForm = this.fb.group({
      nome: ['', Validators.required],
      email: ['', Validators.required],
      endereco: ['', Validators.required],
      telefone: ['', Validators.required],
    });
  }
  ProdutosFindAll(): void {
    this.produtosService.ProdutosfinAll().subscribe(
      (dados) => {
        this.produtos = dados;
        console.log('Produtos:', this.produtos);
      },
      (error) => {
        console.error('Erro ao buscar produtos', error);
      }
    );
  }
  ClienteFindAll(): void {
    this.clienteServico.ClientesfinAll().subscribe(
      (dados) => {
        this.clientes = dados;
        console.log('Clientes:', this.clientes);
      },
      (error) => {
        console.error('Erro ao buscar clientes', error);
      }
    );
  }

  onSubmit(): void {
    if (this.clienteForm.valid && this.faturasForm.valid) {
      const clienteDados: Clientes = {
        nome: this.clienteForm.value.nome,
        email: this.clienteForm.value.email,
        endereco: this.clienteForm.value.endereco,
        telefone: this.clienteForm.value.telefone,
      };

      this.clienteServico.adicionarClientes(clienteDados).subscribe(
        (responseCliente) => {
          this.toastr.success('Cliente adicionado com sucesso');
          this.ClienteFindAll(); // Atualiza a lista de clientes

          // Espera um momento para a lista de clientes ser atualizada
          setTimeout(() => {
            const clienteId = this.getClienteIdByName(
              this.clienteForm.value.nome
            );

            if (clienteId !== null && clienteId !== undefined) {
              const faturaDados: Fatura = {
                dataEmissao: this.faturasForm.get('dataEmissao')!.value,
                valorTotal: this.faturasForm.get('valorTotal')!.value,
                quantidade: this.faturasForm.get('quantidade')!.value,
                produtoId: this.faturasForm.get('produtoId')!.value,
                clienteId: clienteId, // ID encontrado
                categoriaId: this.faturasForm.get('categoriaId')!.value,
                marcaId: this.faturasForm.get('marcaId')!.value,
                usuarioId: this.faturasForm.get('usuarioId')!.value,
              };

              // Aplicar desconto se a quantidade for maior ou igual a 3
              if (faturaDados.quantidade >= 3) {
                faturaDados.valorTotal *= 0.86; // Aplicando desconto de 14%
              }

              this.serverFaturas.adicionarFaturas(faturaDados).subscribe(
                (responseFatura) => {
                  this.toastr.success('Fatura adicionada com sucesso');
                  this.generatePDF(faturaDados);
                  console.log('Fatura adicionada com sucesso:', responseFatura);
                },
                (errorFatura) => {
                  this.toastr.error('Erro ao adicionar fatura');
                  console.error('Erro ao adicionar fatura:', errorFatura);
                }
              );
            } else {
              this.toastr.error(
                'Erro ao buscar ID do cliente recém-adicionado'
              );
              console.error('Erro ao buscar ID do cliente recém-adicionado');
            }
          }, 1000); // Espera de 1 segundo para garantir que a lista de clientes seja atualizada
        },
        (errorCliente) => {
          this.toastr.error('Erro ao adicionar cliente');
          console.error('Erro ao adicionar cliente:', errorCliente);
        }
      );
    }
  }
  getClienteIdByName(nome: string): number | undefined {
    const cliente = this.clientes.find((cliente) => cliente.nome === nome);
    return cliente?.id;
  }

  generatePDF(fatura: Fatura): void {
    const doc = new jsPDF();

    doc.text('Fatura', 10, 10);
    doc.text(`Data de Emissão: ${fatura.dataEmissao}`, 10, 20);
    doc.text(`Quantidade: ${fatura.quantidade}`, 10, 30);
    doc.text(`Valor Total: ${fatura.valorTotal}`, 10, 40);

    doc.save('fatura.pdf');
  }

  onProdutoSelect(produtoId: number): void {
    const produto = this.produtos.find((p) => p.id === produtoId);
    if (produto) {
      this.faturasForm.patchValue({
        categoriaId: produto.categoriaId,
        marcaId: produto.marcaId,
        valorTotal: produto.preco, // Preenche o valor total com o preço do produto
      });
    }
  }
}
