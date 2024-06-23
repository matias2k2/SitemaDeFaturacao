package tinario9945.gmail.com.SistemaFauracao.Models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Catigorias")
@Getter
@Setter
public class Catigoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 45, nullable = false, unique = true)
    private String name;

    // Relacionamento 1 para muitos
    // Obs : na classe onde recebe o relacioname 1 para muito de possou um arry list
    @ManyToOne
    @JoinColumn(name = "marcas_id")
    private Marcas marcas;

}
