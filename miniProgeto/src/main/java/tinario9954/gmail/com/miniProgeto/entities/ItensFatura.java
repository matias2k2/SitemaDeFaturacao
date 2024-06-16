package tinario9954.gmail.com.miniProgeto.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ItensFaturas" )
public class ItensFatura {

    //item_id
    //fatura_id
    //produto_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quantidade;
    private float preco_unitari;
    private int subtotal;

    // gette e setter
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

    
}
