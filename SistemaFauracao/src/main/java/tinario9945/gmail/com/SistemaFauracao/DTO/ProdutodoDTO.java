package tinario9945.gmail.com.SistemaFauracao.DTO;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Produtos;

@Getter
@Setter
public class ProdutodoDTO {
    private Integer id;
    private String nomeProduto;
    private double preco;
    private Integer marcaId;
    private Integer categoriaId;

    public ProdutodoDTO(Produtos entity) {
        this.id = entity.getId();
        this.nomeProduto = entity.getNomeProduto();
        this.preco = entity.getPreco();
        this.marcaId = entity.getMarca().getId();
        this.categoriaId = entity.getCategoria().getId();
    }

    public ProdutodoDTO() {
    }

}
