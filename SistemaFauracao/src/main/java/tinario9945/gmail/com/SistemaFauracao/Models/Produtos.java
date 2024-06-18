package tinario9945.gmail.com.SistemaFauracao.Models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Produtos")
@Getter
@Setter
public class Produtos implements Serializable    {

    private static final long serialVersionUID =1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Produtosid;
    private String  nameProdutos;
    private String  Descricao;
    private double preco;
  

    //hashcode
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

    

    
}
