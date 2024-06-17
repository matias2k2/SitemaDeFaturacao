package tinario9945.gmail.com.SistemaFauracao.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="Faturas")
public class Fatura {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int fatura_id;
    private LocalDate data_emissao;
    private float total;
    
    //FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id),
    //FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id)

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
