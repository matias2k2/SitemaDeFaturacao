package tinario9945.gmail.com.SistemaFauracao.DTO;

import tinario9945.gmail.com.SistemaFauracao.Models.Catigoria;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatigoriaDto {

    private Integer id;
    private String nome;
    private Integer marcaId;

    public CatigoriaDto(Catigoria catigoria) {
        this.id = catigoria.getId();
        this.nome = catigoria.getNome();
        this.marcaId = catigoria.getMarca().getId();

    }

    public CatigoriaDto() {
    }

}
