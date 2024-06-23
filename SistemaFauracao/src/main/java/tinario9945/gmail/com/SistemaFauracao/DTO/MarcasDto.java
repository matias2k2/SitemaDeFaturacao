package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MarcasDto {
  
    private Integer id;
    private String nome;
    private Set<CatigoriaDto> categorias;
 
}
