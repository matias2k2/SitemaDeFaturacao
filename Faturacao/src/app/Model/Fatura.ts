import { Produtos } from "./Produtos";

export interface Fatura {
  produtos: any;
  dataEmissao: string;
  valorTotal: number;
  quantidade: number;
  produtoId: number;
  clienteId: number;
  categoriaId: number;
  marcaId: number;
  usuarioId: number;
}
