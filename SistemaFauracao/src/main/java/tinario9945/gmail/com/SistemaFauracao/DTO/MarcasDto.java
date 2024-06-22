package tinario9945.gmail.com.SistemaFauracao.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import tinario9945.gmail.com.SistemaFauracao.Models.Catigoria;
import tinario9945.gmail.com.SistemaFauracao.Models.Marcas;


@Getter
@Setter
public class MarcasDto {
  
    private Integer id;
    private String nome;
    private List<CatigoriaDto> catigorias;

    public MarcasDto() {}

    public MarcasDto(Marcas marcas) {
        this.id = marcas.getId();
        this.nome = marcas.getNome();
        if (marcas.getCatigoria() != null) {
            // Aqui estamos a fazer um casting 
            this.catigorias = marcas.getCatigoria().stream()
                .map(CatigoriaDto::new)
                .collect(Collectors.toList());
        }
    }
 
}
