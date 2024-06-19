package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;
import tinario9945.gmail.com.SistemaFauracao.Models.ItensFatura;

@Getter
@Setter
public class FaturaDto {
    
    private int fatura_id;
    private Instant data_emissao;
    private float total;
    private List<ItensFatura> itensfatura;

    public FaturaDto(Fatura fatura) {
        this.fatura_id = fatura.getFatura_id();
        this.data_emissao = fatura.getData_emissao();
        this.total = fatura.getTotal();
        this.itensfatura= new ArrayList<>();
        
    }

    // Construtor padrão
    public FaturaDto(Fatura fatura, List<ItensFatura> itensfatura) {
        this(fatura);  

        if (itensfatura != null) {
            this.itensfatura.addAll(itensfatura);  
        }
    }

    public FaturaDto(){}

}
