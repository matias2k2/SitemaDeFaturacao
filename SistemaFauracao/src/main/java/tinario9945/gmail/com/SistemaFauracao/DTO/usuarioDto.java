package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;
import tinario9945.gmail.com.SistemaFauracao.Models.usuario;

@Getter
@Setter
public class usuarioDto {
    
    private Integer  id;
    private String nome;
    private String email;
    private String senha; // Você pode optar por não incluir a senha no DTO por motivos de segurança

    public usuarioDto(usuario entity) {
        this.id = entity.getUsuario_id();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.senha = entity.getSenha(); // Cuidado ao expor a senha
    }

    public usuarioDto() {
    }
    
    

}
