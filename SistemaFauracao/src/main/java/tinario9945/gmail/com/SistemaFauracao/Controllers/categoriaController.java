package tinario9945.gmail.com.SistemaFauracao.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tinario9945.gmail.com.SistemaFauracao.DTO.CatigoriaDto;
import tinario9945.gmail.com.SistemaFauracao.DTO.MarcasDto;
import tinario9945.gmail.com.SistemaFauracao.Services.CatigoriaServices;

@RestController
@RequestMapping("/categoria")
public class categoriaController {
    @Autowired
    private CatigoriaServices servicoes;
    
    @GetMapping
    public List<CatigoriaDto> findAll() {
        List<CatigoriaDto> result = servicoes.findAll();
        return result;
    }
    @PostMapping
    public ResponseEntity<CatigoriaDto> insert(@RequestBody CatigoriaDto dto) {
        CatigoriaDto createdDto = servicoes.insert(dto);
        return ResponseEntity.ok().body(createdDto);
    }
}
