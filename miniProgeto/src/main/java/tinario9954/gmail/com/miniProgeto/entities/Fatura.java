package tinario9954.gmail.com.miniProgeto.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import javafx.scene.chart.PieChart.Data;

@Entity
@Table(name ="Faturas")
public class Fatura {
    private  int fatura_id;
    private Data data_emissao;
    private float total;
    public int getFatura_id() {
        return fatura_id;
    }
    public void setFatura_id(int fatura_id) {
        this.fatura_id = fatura_id;
    }
    public Data getData_emissao() {
        return data_emissao;
    }
    public void setData_emissao(Data data_emissao) {
        this.data_emissao = data_emissao;
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }



    

    //Aqui vamos adiconar o relaconamentoe ente a tabelas cliente_id
}
