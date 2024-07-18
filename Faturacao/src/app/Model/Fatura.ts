import { Clientes } from "./Clientes";
import { ItensFaturas } from "./ItensFaturas";
import { Produtos } from "./Produtos";

export interface Fatura {
  valorTotal: number;
  cliente: Clientes;
  usuarioId: number;
  itens: ItensFaturas[];
}
