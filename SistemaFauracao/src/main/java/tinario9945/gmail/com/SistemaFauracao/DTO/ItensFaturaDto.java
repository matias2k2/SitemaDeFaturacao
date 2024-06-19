package tinario9945.gmail.com.SistemaFauracao.DTO;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.ItensFatura;
@Getter
@Setter
public class ItensFaturaDto {

    private int quantidade;
    private float preco_unitari;
    private int subtotal;

     //Contrutor da nossa classe mae 
    public ItensFaturaDto(ItensFatura ItensFaturas) {
        this.quantidade = ItensFaturas.getQuantidade();
        this.preco_unitari = ItensFaturas.getPreco_unitari();
        this.subtotal = ItensFaturas.getSubtotal();
    }
    public ItensFaturaDto() {
    }
    

    
    
    
}
