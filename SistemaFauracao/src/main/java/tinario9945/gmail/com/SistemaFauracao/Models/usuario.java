package tinario9945.gmail.com.SistemaFauracao.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
    private Integer usuario_id;
    private String nome ;
    private String email;
    private String senha;


    

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Fatura> faturas;


}
