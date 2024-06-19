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
@Table(name = "ItensFaturas")
@Getter
@Setter
public class ItensFatura implements Serializable{
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itesns_id;
    
    private int quantidade;
    private float preco_unitari;
    private int subtotal;


}
