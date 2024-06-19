package tinario9945.gmail.com.SistemaFauracao.Models;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.mapping.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="Faturas")
@Getter
@Setter
public class Fatura implements Serializable{
  
    private static final long serialVersionUID =1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int fatura_id;
    
  
    private Instant data_emissao;
    private float total;
    

    @OneToMany
    @JoinColumn(name = "fkitensfatura_id")
    private List<ItensFatura> itensfatura = new ArrayList<>();


    

    public Fatura() {
    }

    public Fatura(int fatura_id, Instant data_emissao, float total) {
        this.fatura_id = fatura_id;
        this.data_emissao = data_emissao;
        this.total = total;
    }

    

    
}
