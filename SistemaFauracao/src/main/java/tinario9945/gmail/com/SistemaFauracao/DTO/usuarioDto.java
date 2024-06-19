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
    
    private int usuario_id;
    private String nome ;
    private String email;
    private String senha;
    private List<FaturaDto> faturas;
    public usuarioDto() {
    }

    public usuarioDto(usuario usuario) {
        this.usuario_id = usuario.getUsuario_id();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.faturas = new ArrayList<>();
    }

    public usuarioDto(usuario usuario,List<FaturaDto> faturas) {
        this(usuario);
        this.faturas = faturas;
    }
    
    

}
