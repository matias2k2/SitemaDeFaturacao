package tinario9954.gmail.com.miniProgeto.DTO;

import javafx.scene.chart.PieChart.Data;
import tinario9954.gmail.com.miniProgeto.entities.Fatura;

public class FaturaDto {
    

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


    public FaturaDto(Fatura faturas ) {
        this.fatura_id = faturas.getFatura_id();
        this.data_emissao = faturas.getData_emissao();
        this.total = faturas.getTotal();
    }



    
}
