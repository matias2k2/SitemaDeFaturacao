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
import { ItensFaturas } from 'src/app/Model/ItensFaturas';

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
  itens: ItensFaturas[] = [];
  valorTotal: number = 0;

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
      valorTotal: [{ value: '', disabled: true }, Validators.required],
    });
  }

  onClienteSubmit(): void {
    const cliente = {
      nome: this.clienteForm.value.nomeCliente,
      endereco: this.clienteForm.value.enderecoCliente,
      telefone: this.clienteForm.value.telefoneCliente,
      email: this.clienteForm.value.emailCliente,
    };

    this.clienteServico.adicionarClientes(cliente).subscribe(
      () => {
        this.clienteRegistrado = true;
        this.toastr.success('Cliente registrado com sucesso!');
      },
      (error) => {
        this.toastr.error('Erro ao registrar cliente');
        console.error('Erro ao registrar cliente:', error);
      }
    );
  }

  onProdutoChange(event: any): void {
    const nomeProduto = event.target.value;
    this.selectedProduct = this.getProdutoByName(nomeProduto);
    if (this.selectedProduct) {
      this.produtoForm.patchValue({
        precoProduto: this.selectedProduct.preco,
        nomeProduto: this.selectedProduct.nomeProduto,
      });
    }
  }

  onProdutoSubmit(): void {
    const quantidade = this.produtoForm.value.quantidade;
    const precoProduto = this.selectedProduct?.preco || 0;
    const estoqueDisponivel = this.selectedProduct?.quantidade || 0;

    if (quantidade > estoqueDisponivel) {
      this.toastr.error(
        'Não há quantidade suficiente deste produto disponível.'
      );
      return;
    }

    let valorTotal = quantidade * precoProduto;
    if (quantidade >= 3) {
      valorTotal *= 0.86; // Aplicando desconto de 14%
    }

    const item: ItensFaturas = {
      quantidade: quantidade,
      produtoId: this.selectedProduct?.id || 0,
      categoriaId: this.selectedProduct?.categoriaId || 0,
      marcaId: this.selectedProduct?.marcaId || 0,
      nomeProdutos: this.produtoForm.value.nomeProduto,
      valorTotal: valorTotal,
    };
    this.itens.push(item);
    this.calculateFaturaTotal();
    this.produtoForm.reset();
    this.selectedProduct = undefined; // Reseta o produto selecionado
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

  calculateFaturaTotal(): void {
    this.valorTotal = this.itens.reduce(
      (total, item) => total + item.valorTotal,
      0
    );
  }

  confirmarFatura(): void {
    const clienteId = this.getClienteIdByName(
      this.clienteForm.value.nomeCliente
    );
    if (!clienteId) {
      this.toastr.error('Selecione um cliente válido.');
      return;
    }

    const fatura: Fatura = {
      cliente: {
        id: clienteId,
        nome: this.clienteForm.value.nomeCliente,
        endereco: this.clienteForm.value.enderecoCliente,
        telefone: this.clienteForm.value.telefoneCliente,
        email: this.clienteForm.value.emailCliente,
      },
      valorTotal: this.valorTotal,
      usuarioId: 1, // Substitua pelo ID do usuário autenticado
      itens: this.itens,
    };

    this.faturaServicos.gerarFatura(fatura).subscribe(
      () => {
        this.toastr.success('Fatura criada com sucesso!');
        this.generatePDF(fatura);
        this.resetForm();
      },
      (error) => {
        this.toastr.error('Erro ao criar fatura');
        console.error('Erro ao criar fatura:', error);
      }
    );
  }

  resetForm(): void {
    this.clienteForm.reset();
    this.produtoForm.reset();
    this.itens = [];
    this.valorTotal = 0;
    this.clienteRegistrado = false;
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

  generatePDF(fatura: Fatura): void {
    const doc = new jsPDF();

    doc.setFontSize(16);
    doc.setTextColor(0, 0, 255);
    doc.text('     ============= Fatura De Compre =============', 10, 10);
    doc.text('', 10, 10);
    doc.text('', 10, 10);

    doc.setFontSize(12);
    doc.setTextColor(0, 0, 0);
    doc.text(`Cliente: ${fatura.cliente.nome}`, 10, 20);
    doc.text(`Endereço: ${fatura.cliente.endereco}`, 10, 30);
    doc.text(`Telefone: ${fatura.cliente.telefone}`, 10, 40);
    doc.text(`Email: ${fatura.cliente.email}`, 10, 50);
    doc.text(`Data de Emissão: ${new Date().toLocaleDateString()}`, 10, 60);
    doc.text(`Valor Total: R$ ${fatura.valorTotal.toFixed(2)}`, 10, 70);

    let yPosition = 80;
    fatura.itens.forEach((item, index) => {
      doc.text(
        `${index + 1}. ${item.nomeProdutos} - Quantidade: ${
          item.quantidade
        } - R$ ${item.valorTotal.toFixed(2)}`,
        10,
        yPosition
      );
      yPosition += 10;
    });

    doc.text(
      `Valor Total da Fatura: R$ ${fatura.valorTotal.toFixed(2)}`,
      10,
      yPosition + 10
    );

    doc.save('fatura.pdf');
  }
  cancelarAdicao(): void {
    this.produtoForm.reset();
    this.selectedProduct = undefined;
  }
}
