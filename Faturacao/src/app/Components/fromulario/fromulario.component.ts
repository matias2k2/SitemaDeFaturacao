import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  clienteForm!: FormGroup;
  produtoForm!: FormGroup;
  clienteRegistrado: boolean = false;
  selectedProduct: Produtos | undefined;

  nome1 : string | undefined;

  constructor(
    private fb: FormBuilder,
    private clienteServico: ClientesService,
    private toastr: ToastrService,
    private produtosService: ProdutosService,
    private faturaServicos: FaturasService
  ) {}

  ngOnInit(): void {
    this.initForms();
    this.ClienteFindAll();
    this.ProdutosFindAll();
  }

  initForms(): void {
    this.clienteForm = this.fb.group({
      nomeCliente: ['', Validators.required],
      enderecoCliente: ['', Validators.required],
      telefoneCliente: ['', Validators.required],
      emailCliente: ['', [Validators.required, Validators.email]],
    });

    this.produtoForm = this.fb.group({
      nomeProduto: ['', Validators.required],
      precoProduto: [{ value: '', disabled: true }, Validators.required],
      dataEmissao: ['', Validators.required],
      quantidade: ['', Validators.required],
      valorTotal: ['',  Validators.required],
    });

  }

  onClienteSubmit(): void {
    if (this.clienteForm.valid) {
      const clienteDados = {
        nome: this.clienteForm.value.nomeCliente,
        email: this.clienteForm.value.emailCliente,
        endereco: this.clienteForm.value.enderecoCliente,
        telefone: this.clienteForm.value.telefoneCliente,
      };
      this.nome1 = this.clienteForm.value.nomeCliente;

      this.clienteServico.adicionarClientes(clienteDados).subscribe(
        (responseCliente) => {
          this.toastr.success('Cliente registrado com sucesso!');
          this.clienteRegistrado = true;
          this.ClienteFindAll(); // Atualiza a lista de clientes
          this.clienteForm.reset();
          this.ProdutosFindAll(); // Atualiza a lista de produtos
        },
        (errorCliente) => {
          this.toastr.error('Erro ao registrar cliente');
          console.error('Erro ao registrar cliente:', errorCliente);
        }
      );
    } else {
      this.toastr.error('Por favor, preencha todos os campos obrigatórios.');
    }
  }

  onProdutoSubmit(): void {
    if (this.produtoForm.valid) {
      const nomeCliente = this.nome1;

      console.log('resedxxxxxxxxx', this.nome1);
      if (!nomeCliente) {
        this.toastr.error('Por favor, selecione um cliente.');
        return;
      }

      const clienteId = this.getClienteIdByName(nomeCliente);
      if (clienteId === undefined) {
        this.toastr.error('Cliente não encontrado ou ID não obtido.');
        return;
      }

      const nomeProduto = this.produtoForm.value.nomeProduto;
      if (!nomeProduto) {
        this.toastr.error('Por favor, selecione um produto.');
        return;
      }

      const marcaId = this.getMarcaIdByProduto(nomeProduto);
      const categoriaId = this.getCategoriaIdByProduto(nomeProduto);

      console.log('clienteId:', clienteId);
      console.log('marcaId:', marcaId);
      console.log('categoriaId:', categoriaId);

      if (
        this.selectedProduct?.id &&
        marcaId !== undefined &&
        categoriaId !== undefined
      ) {
        let valorTotal = this.produtoForm.value.valorTotal;

        if (this.produtoForm.value.quantidade >= 3) {
          valorTotal *= 0.86; // Aplicando desconto de 14%
        }

        const faturaDados: Fatura = {
          dataEmissao: this.produtoForm.value.dataEmissao,
          valorTotal: Number(valorTotal),
          quantidade: this.produtoForm.value.quantidade,
          produtoId: Number(this.selectedProduct.id),
          clienteId: clienteId,
          categoriaId: Number(categoriaId),
          marcaId: Number(marcaId),
          usuarioId: 1,
          produtos: undefined
        };

        console.log('faturaDados:', faturaDados);

        this.faturaServicos.adicionarFaturas(faturaDados).subscribe(
          (responseFatura) => {
            this.toastr.success('Fatura adicionada com sucesso');
            this.generatePDF(faturaDados);
            this.produtoForm.reset();
          },
          (errorFatura) => {
            this.toastr.error('Erro ao adicionar fatura');
            console.error('Erro ao adicionar fatura:', errorFatura);
          }
        );
      } else {
        this.toastr.error(
          'Erro ao buscar ID do cliente, produto, marca ou categoria.'
        );
      }
    } else {
      this.toastr.error('Por favor, preencha todos os campos obrigatórios.');
    }
  }

  onProdutoChange(event: any): void {
    const nomeProduto = event.target.value;
    this.selectedProduct = this.produtos.find(
      (product) => product.nomeProduto === nomeProduto
    );

    if (this.selectedProduct) {
      this.produtoForm.patchValue({
        precoProduto: this.selectedProduct.preco,
      });
      this.calculateTotal();
    }
  }

  calculateTotal(): void {
    const quantidade = this.produtoForm.value.quantidade;
    const precoProduto = this.selectedProduct?.preco;
    if (quantidade && precoProduto) {
      let valorTotal = quantidade * precoProduto;
      if (quantidade >= 3) {
        valorTotal *= 0.86; // Aplicando desconto de 14%
      }
      this.produtoForm.patchValue({
        valorTotal: valorTotal.toFixed(2),
      });
    }
  }

  ProdutosFindAll(): void {
    this.produtosService.ProdutosfinAll().subscribe(
      (dados) => {
        this.produtos = dados;
        console.log('Produtos:', this.produtos);
      },
      (error) => {
        this.toastr.error('Erro ao buscar produtos');
        console.error('Erro ao buscar produtos:', error);
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
        this.toastr.error('Erro ao buscar clientes');
        console.error('Erro ao buscar clientes:', error);
      }
    );
  }
  getProdutoByName(nome: string): Produtos | undefined {
    if (!nome) {
      console.error('Nome do produto não pode ser nulo ou indefinido');
      return undefined;
    }

    const produto = this.produtos.find(
      (produto) => produto.nomeProduto === nome.trim()
    );
    console.log('Produto encontrado:', produto);
    return produto;
  }

  getClienteIdByName(nome: string): number | undefined {
    if (!nome) {
      console.error('Nome do cliente não pode ser nulo ou indefinido');
      return undefined;
    }

    const cliente = this.clientes.find(
      (cliente) => cliente.nome === nome.trim()
    );

    if (!cliente) {
      console.error('Cliente não encontrado para o nome:', nome);
      return undefined;
    }

    console.log('Cliente encontrado:', cliente);
    return cliente.id;
  }

  getMarcaIdByProduto(nome: string): number | undefined {
    const produto = this.getProdutoByName(nome);
    if (produto) {
      console.log('Marca do produto:', produto.marcaId);
      return produto.marcaId;
    }
    console.error('Produto não encontrado para o nome:', nome);
    return undefined;
  }

  getCategoriaIdByProduto(nome: string): number | undefined {
    const produto = this.getProdutoByName(nome);
    if (produto) {
      console.log('Categoria do produto:', produto.categoriaId);
      return produto.categoriaId;
    }
    console.error('Produto não encontrado para o nome:', nome);
    return undefined;
  }

  generatePDF(fatura: Fatura): void {
    const doc = new jsPDF();

    doc.text('============= Fatura De Compre =============', 10, 10);
    doc.text('', 10, 10);
    doc.text('', 10, 10);
    doc.text('===  Matias goncalves  Adelina ', 10, 10);
    doc.text(`=== Data de Emissão: ${fatura.dataEmissao}`, 10, 20);
    doc.text(`=== Quantidade: ${fatura.quantidade}`, 10, 30);
    doc.text(`=== Valor Total: ${fatura.valorTotal}     `, 10, 40);
    doc.text(`=== CLiente : ${this.nome1}     `, 10, 40);

    doc.save('fatura.pdf');
  }
}
