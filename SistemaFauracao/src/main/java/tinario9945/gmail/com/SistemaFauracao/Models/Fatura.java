package tinario9945.gmail.com.SistemaFauracao.Models;

import java.time.LocalDate;
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
@Table(name ="Faturas")
public class Fatura {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int fatura_id;
    private LocalDate data_emissao;
    private float total;
    
    @OneToMany
    @JoinColumn(name = "cliente_id")
    private List<Cliente> clientes = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "usuario_id")
    private List<usuario> usuarios = new ArrayList<>();


    public int getFatura_id() {
        return fatura_id;
    }
    public void setFatura_id(int fatura_id) {
        this.fatura_id = fatura_id;
    }
    public LocalDate getData_emissao() {
        return data_emissao;
    }
    public void setData_emissao(LocalDate data_emissao) {
        this.data_emissao = data_emissao;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }

    
}
