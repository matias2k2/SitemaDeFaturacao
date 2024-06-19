package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;

@Getter
@Setter
public class FaturaDto {
    
    private int fatura_id;
    private Instant data_emissao;
    private float total;
    private List<ClienteDto> clientes;
    private List<usuarioDto > usuarios;

    public FaturaDto(Fatura fatura) {
        this.fatura_id = fatura.getFatura_id();
        this.data_emissao = fatura.getData_emissao();
        this.total = fatura.getTotal();
        this.clientes= new ArrayList<>();
        this.usuarios= new ArrayList<>();
    }

    // Construtor padrão
    public FaturaDto(Fatura fatura, List<ClienteDto> clientes, List<usuarioDto> usuarios) {
        this(fatura);  

        if (clientes != null) {
            this.clientes.addAll(clientes);  
        }

        if (usuarios != null) {
            this.usuarios.addAll(usuarios);  
        }
    }
    


}
