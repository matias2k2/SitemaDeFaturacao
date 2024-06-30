export interface Fatura {
  dataEmissao: string; // Use 'string' para datas
  valorTotal: number; // Use 'number' para valores numéricos
  quantidade: number; // Use 'number' para valores numéricos
  produtoId: number; // Use 'number' para IDs
  clienteId: number; // Use 'number' para IDs
  categoriaId: number; // Use 'number' para IDs
  marcaId: number; // Use 'number' para IDs
  usuarioId: number | null; // Use 'number' para IDs, pode ser null
}
