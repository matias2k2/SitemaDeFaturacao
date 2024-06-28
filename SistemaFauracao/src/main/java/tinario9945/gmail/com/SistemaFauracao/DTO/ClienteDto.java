package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Cliente;

@Getter
@Setter
public class ClienteDto implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    

    public ClienteDto(Cliente clientes) {
        this.id = clientes.getId();
        this.nome = clientes.getNome();
        this.endereco = clientes.getEndereco();
        this.telefone = clientes.getTelefone();
        this.email = clientes.getEmail();
    }


    public ClienteDto() {

    }

   

}
