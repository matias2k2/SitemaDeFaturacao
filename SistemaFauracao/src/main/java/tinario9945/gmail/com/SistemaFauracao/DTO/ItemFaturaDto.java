package tinario9945.gmail.com.SistemaFauracao.DTO;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.ItemFatura;

@Getter
@Setter
public class ItemFaturaDto {
    private Integer id;
    private int quantidade;
    private Integer produtoId;
    private Integer categoriaId;
    private Integer marcaId;
    private String nomeProdutos;

    // Construtor para mapear a entidade para o DTO
    public ItemFaturaDto(ItemFatura itemFatura) {
        this.id = itemFatura.getId();
        this.quantidade = itemFatura.getQuantidade();
        this.produtoId = itemFatura.getProduto().getId();
        this.categoriaId = itemFatura.getCategoria().getId();
        this.marcaId = itemFatura.getMarca().getId();
        this.nomeProdutos=itemFatura.getNomeProdutos();
    }
    public ItemFaturaDto(){}
}
