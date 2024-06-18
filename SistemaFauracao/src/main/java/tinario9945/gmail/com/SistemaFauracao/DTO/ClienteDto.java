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
    private String  nome;
    private String endereco;
    private String telefone;
    private String  email;
   // private List<FaturaDto> faturas = new ArrayList<>();

    //Getter e Setter
    public int getCliente_id() {
        return cliente_id;
    }
    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


   
    public ClienteDto(Cliente clientes) {
        this.cliente_id = clientes.getCliente_id();
        this.nome = clientes.getNome();
        this.endereco = clientes.getEndereco();
        this.telefone = clientes.getTelefone();
        this.email = clientes.getEmail();
    }
}
