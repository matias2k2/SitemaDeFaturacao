package tinario9954.gmail.com.miniProgeto.entities;

import java.io.Serializable;

public class Produtos implements Serializable{
    


    private Long Produtosid;
    private String  nameProdutos;
    private String  Descricao;
    private double preco;

    
   
   
    public Long getProdutosid() {
        return Produtosid;
    }
    public void setProdutosid(Long produtosid) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Produtosid == null) ? 0 : Produtosid.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produtos other = (Produtos) obj;
        if (Produtosid == null) {
            if (other.Produtosid != null)
                return false;
        } else if (!Produtosid.equals(other.Produtosid))
            return false;
        return true;
    }
    public Produtos(Long produtosid, String nameProdutos, String descricao, double preco) {
        Produtosid = produtosid;
        this.nameProdutos = nameProdutos;
        Descricao = descricao;
        this.preco = preco;
    }

    

}
