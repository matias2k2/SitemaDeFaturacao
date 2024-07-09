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

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Faturas")
@Getter
@Setter
public class Fatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Double valorTotal;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produto;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Catigoria categoria;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marcas marca;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private usuario usuario;

    public Fatura() {
    }

    public Fatura(Integer id, 
             
            Double valorTotal, int quantidade, Produtos produto, Cliente cliente,
            Catigoria categoria, Marcas marca) {
        this.id = id;
        
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
        this.produto = produto;
        this.cliente = cliente;
        this.categoria = categoria;
        this.marca = marca;
    }

}
