package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Marcas;


@Getter
@Setter
public class MarcasDto implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;

    // Construtor que recebe uma entidade Marcas
    public MarcasDto(Marcas marcas) {
        this.id = marcas.getId();
        this.nome = marcas.getNome();
    }

    public MarcasDto() {
    }
    

 
}
