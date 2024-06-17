package tinario9945.gmail.com.SistemaFauracao.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter 
public class usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuario_id;
    private String nome ;
    private String email;
    private String senha;



}
