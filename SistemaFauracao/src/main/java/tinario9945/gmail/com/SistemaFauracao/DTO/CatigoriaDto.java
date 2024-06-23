package tinario9945.gmail.com.SistemaFauracao.DTO;
import tinario9945.gmail.com.SistemaFauracao.Models.Catigoria;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CatigoriaDto {
    private Integer id;
    private String name;
    private Integer marcasId; // Chave estrangeira

    public CatigoriaDto(Catigoria catigoria) {
        this.id = catigoria.getId();
        this.name = catigoria.getName();
        if (catigoria.getMarcas() != null) {
            this.marcasId = catigoria.getMarcas().getId();

        }
    }

    public CatigoriaDto() {
    }

    
}
