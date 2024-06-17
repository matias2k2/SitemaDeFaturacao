package tinario9945.gmail.com.SistemaFauracao.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Produtos")
public class Produtos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    
}
