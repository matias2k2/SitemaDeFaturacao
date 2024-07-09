import { Component } from '@angular/core';
import { ProdutosService } from 'src/app/services/Produtos/produtos.service';
import { ToastrService } from 'ngx-toastr';
import { Produtos } from 'src/app/Model/Produtos';

@Component({
  selector: 'app-lista-produtos',
  templateUrl: './lista-produtos.component.html',
  styleUrls: ['./lista-produtos.component.scss'],
})
export class ListaProdutosComponent {
  produtos: Produtos[] = [];

  constructor(
    private produtosService: ProdutosService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadProdutos();
  }

  loadProdutos(): void {
    this.produtosService.ProdutosfinAll().subscribe(
      (data) => {
        this.produtos = data;
      },
      (error) => {
        this.toastr.error('Erro ao carregar produtos');
        console.error('Erro ao carregar produtos', error);
      }
    );
  }

  deleteProduto(id: number): void {
  console.log(`Tentando eliminar produto com ID: ${id}`);
  if (id) {
    this.produtosService.deleteProduto(id).subscribe(
      () => {
        this.toastr.success('Produto eliminado com sucesso');
        this.produtos = this.produtos.filter((produto) => produto.id !== id);
      },
      (error) => {
        this.toastr.error('Erro ao eliminar produto');
        console.error('Erro ao eliminar produto', error);
      }
    );
  } else {
    console.error('ID do produto é undefined ou null');
  }
}

}
