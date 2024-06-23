package tinario9945.gmail.com.SistemaFauracao.DTO;
import tinario9945.gmail.com.SistemaFauracao.Models.Catigoria;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CatigoriaDto {
   
    private String name;
     @JsonProperty("marcas_id")
    private Integer marcasId; // Chave estrangeira

    public CatigoriaDto(Catigoria catigoria) {
        
        this.name = catigoria.getName();
        if (catigoria.getMarcas() != null) {
            this.marcasId = catigoria.getMarcas().getId();

        }
    }

    public CatigoriaDto() {
    }

    
}
