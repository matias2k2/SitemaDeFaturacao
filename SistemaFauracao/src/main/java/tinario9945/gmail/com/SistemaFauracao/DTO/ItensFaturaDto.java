package tinario9945.gmail.com.SistemaFauracao.DTO;

import tinario9945.gmail.com.SistemaFauracao.Models.ItensFatura;

public class ItensFaturaDto {

        private int quantidade;
    private float preco_unitari;
    private int subtotal;

    //Grtter e Setter 
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
     //Contrutor da nossa classe mae 
    public ItensFaturaDto(ItensFatura ItensFaturas) {
        this.quantidade = ItensFaturas.getQuantidade();
        this.preco_unitari = ItensFaturas.getPreco_unitari();
        this.subtotal = ItensFaturas.getSubtotal();
    }
    
}
