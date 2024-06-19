package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Produtos;

@Getter
@Setter
public class ProdutodoDTO {
    private Integer Produtosid;
    private String nameProdutos;
    private String Descricao;
    private double preco;
    private List<ItensFaturaDto> itensfatura;

    public ProdutodoDTO(Produtos entity) {

        this.Produtosid = entity.getProdutosid();
        this.nameProdutos = entity.getNameProdutos();
        this.Descricao = entity.getDescricao();
        this.preco = entity.getPreco();
        this.itensfatura = new ArrayList<>();
    }

    public ProdutodoDTO(Produtos entity, List<ItensFaturaDto> itensfatura) {
        this(entity);  
        this.itensfatura = itensfatura;
    }
    public ProdutodoDTO(){}

}
