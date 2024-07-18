package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;

@Getter
@Setter
public class FaturaDto implements Serializable{
    private Integer id;
    private Double valorTotal;
    private ClienteDto cliente;
    private Integer usuarioId;
    private List<ItemFaturaDto> itens;

    public FaturaDto() {
    }

    public FaturaDto(Fatura fatura) {
        this.id = fatura.getId();
        this.valorTotal = fatura.getValorTotal();
        this.cliente = new ClienteDto(fatura.getCliente());
        this.usuarioId = fatura.getUsuario().getUsuario_id();
        this.itens = fatura.getItens().stream().map(ItemFaturaDto::new).collect(Collectors.toList());
    }

}
