package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;

@Getter
@Setter
public class FaturaDto {
     private  int fatura_id;
    private LocalDate data_emissao;
    private float total;
    public int getFatura_id() {
        return fatura_id;
    }
    public void setFatura_id(int fatura_id) {
        this.fatura_id = fatura_id;
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
