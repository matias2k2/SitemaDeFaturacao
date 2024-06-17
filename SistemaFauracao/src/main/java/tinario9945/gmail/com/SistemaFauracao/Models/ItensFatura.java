package tinario9945.gmail.com.SistemaFauracao.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ItensFaturas")
public class ItensFatura {
    // item_id
    // fatura_id
    // produto_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quantidade;
    private float preco_unitari;
    private int subtotal;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<ItensFatura> itensDafatura = new ArrayList<>();


    @OneToMany
    @JoinColumn(name = "fatura_id")
    private List<Fatura> faturas = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "produto_id")
    private List<Produtos> produtos = new ArrayList<>();

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco_unitari() {
        return preco_unitari;
    }

    public void setPreco_unitari(float preco_unitari) {
        this.preco_unitari = preco_unitari;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public List<ItensFatura> getItensDafatura() {
        return itensDafatura;
    }

    public void setItensDafatura(List<ItensFatura> itensDafatura) {
        this.itensDafatura = itensDafatura;
    }

    public List<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(List<Fatura> faturas) {
        this.faturas = faturas;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    

}
