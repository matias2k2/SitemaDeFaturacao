package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;


@Getter
@Setter
public class FaturaDto {
    
    private int fatura_id;
    private Instant data_emissao;
    private float valorTotal;
    private int quantidade;

    

    public FaturaDto(Fatura fatura) {
        this.fatura_id = fatura.getFatura_id();
        this.data_emissao = fatura.getData_emissao();
        this.valorTotal = fatura.getValorTotal();
        this.quantidade = fatura.getQuantidade();
    }



    public FaturaDto(){}

}
