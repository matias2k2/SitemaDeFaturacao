package tinario9945.gmail.com.SistemaFauracao.DTO;

import tinario9945.gmail.com.SistemaFauracao.Models.Catigoria;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatigoriaDto implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private Integer marcaId;

    public CatigoriaDto(Catigoria catigoria) {
        this.id = catigoria.getId();
        this.nome = catigoria.getNome();
        // Verificação se marca não é nula
        if (catigoria.getMarca() != null) {
            this.marcaId = catigoria.getMarca().getId();
        }

    }

    public CatigoriaDto() {
    }

}
