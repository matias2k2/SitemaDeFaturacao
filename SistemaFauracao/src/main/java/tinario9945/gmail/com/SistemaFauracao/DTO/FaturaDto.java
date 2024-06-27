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

    private Integer id;
    private Instant dataEmissao;
    private float valorTotal;
    private int quantidade;
    private Integer produtoId;
    private Integer clienteId;
    private Integer categoriaId;
    private Integer marcaId;
    private Integer usuarioId;

    public FaturaDto(Fatura fatura) {
        this.id = fatura.getId();
        this.dataEmissao = fatura.getDataEmissao();
        this.valorTotal = fatura.getValorTotal();
        this.quantidade = fatura.getQuantidade();
        this.produtoId = fatura.getProduto().getId();
        this.clienteId = fatura.getCliente().getId();
        this.categoriaId = fatura.getCategoria().getId();
        this.marcaId = fatura.getMarca().getId();
        //this.usuarioId = fatura.getUsuarios().getUsuario_id();

    }

    public FaturaDto() {
    }

}
