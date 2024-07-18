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

import jakarta.persistence.CascadeType;
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

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private usuario usuario;

    @OneToMany(mappedBy = "fatura", cascade = CascadeType.ALL)
    private List<ItemFatura> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Catigoria categoria;

    // Métodos para adicionar e remover itens
    public void adicionarItem(ItemFatura item) {
        itens.add(item);
        item.setFatura(this);
    }

    public void removerItem(ItemFatura item) {
        itens.remove(item);
        item.setFatura(null);
    }

    // Método para calcular o valor total
    public void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .mapToDouble(item -> item.getQuantidade() * item.getProduto().getPreco())
                .sum();
    }

    public Fatura() {
    }

    public Fatura(Integer id, Double valorTotal, Cliente cliente, usuario usuario, List<ItemFatura> itens) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.usuario = usuario;
        this.itens = itens;
    }
}
