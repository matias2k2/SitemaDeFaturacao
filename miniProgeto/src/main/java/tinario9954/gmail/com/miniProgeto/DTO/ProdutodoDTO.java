package tinario9954.gmail.com.miniProgeto.DTO;

import org.apache.catalina.webresources.Cache;

import tinario9954.gmail.com.miniProgeto.entities.Produtos;

public class ProdutodoDTO {
    
    private Integer Produtosid;
    private String  nameProdutos;
    private String  Descricao;
    private double preco;
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

        this.Produtosid =entity.getProdutosid();
        this.nameProdutos=entity.getNameProdutos();
        this.Descricao = entity.getDescricao();
        this.preco=entity.getPreco();
    }


    
    
    
}
