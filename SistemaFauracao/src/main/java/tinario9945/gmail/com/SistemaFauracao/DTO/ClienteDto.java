package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Cliente;

@Getter
@Setter
public class ClienteDto {

    private int cliente_id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    // private List<FaturaDto> faturas = new ArrayList<>();
    public ClienteDto(Cliente clientes) {
        this.cliente_id = clientes.getCliente_id();
        this.nome = clientes.getNome();
        this.endereco = clientes.getEndereco();
        this.telefone = clientes.getTelefone();
        this.email = clientes.getEmail();
    }

    public ClienteDto() {

    }

}
