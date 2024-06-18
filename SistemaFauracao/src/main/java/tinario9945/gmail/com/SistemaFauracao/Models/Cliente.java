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
@Table(name ="Clientes")
@Getter
@Setter
public class Cliente implements Serializable{
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cliente_id;
    private String  nome;
    private String endereco;
    private String telefone;
    private String  email;

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cliente_id;
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
        Cliente other = (Cliente) obj;
        if (cliente_id != other.cliente_id)
            return false;
        return true;
    }
    public Cliente() {
    }
    

  

}
