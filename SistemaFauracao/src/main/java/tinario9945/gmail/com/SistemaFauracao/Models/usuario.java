package tinario9945.gmail.com.SistemaFauracao.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.tokenAcs.userTokens;

@Entity
@Table(name = "usuarios")
@Getter
@Setter 
public class usuario implements Serializable{
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuario_id;
    private String nome ;
    private String email;
    private String senha;

    @OneToMany
    @JoinColumn(name = "fkusuario_id")
    private List<userTokens> usertokens= new ArrayList<>();
    

    @OneToMany
    @JoinColumn(name = "fkusuario_id")
    private List<Fatura> faturas = new ArrayList<>();

    //hasCode
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + usuario_id;
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
        usuario other = (usuario) obj;
        if (usuario_id != other.usuario_id)
            return false;
        return true;
    }

}
