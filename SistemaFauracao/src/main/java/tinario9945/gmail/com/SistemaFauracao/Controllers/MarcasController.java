package tinario9945.gmail.com.SistemaFauracao.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tinario9945.gmail.com.SistemaFauracao.DTO.MarcasDto;
import tinario9945.gmail.com.SistemaFauracao.Services.MarcasServices;

@RestController
@RequestMapping("/marcas")
public class MarcasController {

    @Autowired
    private MarcasServices servicoes;

    @GetMapping
    public List<MarcasDto> findAll() {
        List<MarcasDto> result = servicoes.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MarcasDto> findById(@PathVariable Integer id) {
        MarcasDto resl = servicoes.findById(id);
        return ResponseEntity.ok().body(resl);
    }

    @PostMapping
    public ResponseEntity<MarcasDto> insert(@RequestBody MarcasDto dto) {
        MarcasDto createdDto = servicoes.insert(dto);
        return ResponseEntity.ok().body(createdDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MarcasDto> update(@PathVariable Integer id, @RequestBody MarcasDto dto) {
        MarcasDto createdDto = servicoes.update(dto, id);
        return ResponseEntity.ok().body(createdDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MarcasDto> delectar(@PathVariable Integer id) {
        servicoes.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
