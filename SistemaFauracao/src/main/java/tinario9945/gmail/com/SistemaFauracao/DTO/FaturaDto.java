package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;

@Getter
@Setter
public class FaturaDto implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    
    private Double valorTotal;
    private int quantidade;
    private Integer produtoId;
    private Integer clienteId;
    private Integer categoriaId;
    private Integer marcaId;
    private Integer usuarioId;

    public FaturaDto(Fatura fatura) {
        this.id = fatura.getId();
        
        this.valorTotal = fatura.getValorTotal();
        this.quantidade = fatura.getQuantidade();
        this.produtoId = fatura.getProduto().getId();
        this.clienteId = fatura.getCliente().getId();
        this.categoriaId = fatura.getCategoria().getId();
        this.marcaId = fatura.getMarca().getId();
        this.usuarioId = fatura.getUsuario().getUsuario_id();

    }

    public FaturaDto() {
    }

}
