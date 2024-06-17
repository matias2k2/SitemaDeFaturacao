package tinario9945.gmail.com.SistemaFauracao.DTO;

import tinario9945.gmail.com.SistemaFauracao.Models.Produtos;

public class ProdutodoDTO {
    private Integer Produtosid;
    private String nameProdutos;
    private String Descricao;
    private double preco;

    // Geter e Setter da minha
    public Integer getProdutosid() {
        return Produtosid;
    }

    public void setProdutosid(Integer produtosid) {
        Produtosid = produtosid;
    }

    public String getNameProdutos() {
        return nameProdutos;
    }

    public void setNameProdutos(String nameProdutos) {
        this.nameProdutos = nameProdutos;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public ProdutodoDTO(Produtos entity) {

        this.Produtosid = entity.getProdutosid();
        this.nameProdutos = entity.getNameProdutos();
        this.Descricao = entity.getDescricao();
        this.preco = entity.getPreco();
    }
}
