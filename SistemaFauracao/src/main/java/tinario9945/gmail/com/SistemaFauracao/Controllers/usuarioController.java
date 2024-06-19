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

import tinario9945.gmail.com.SistemaFauracao.DTO.usuarioDto;
import tinario9945.gmail.com.SistemaFauracao.Services.usuarioServices;

@RestController
@RequestMapping("/usuario")
public class usuarioController {
    @Autowired
    private usuarioServices usuarioservices;

    @GetMapping
    public List<usuarioDto> findAll() {
        List<usuarioDto> result = usuarioservices.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<usuarioDto> findById(@PathVariable Long id) {
        usuarioDto resl = usuarioservices.findById(id);
        return ResponseEntity.ok().body(resl);
    }

    @PostMapping
    public ResponseEntity<usuarioDto> insert(@RequestBody usuarioDto dto) {
        usuarioDto createdDto = usuarioservices.insert(dto);
        return ResponseEntity.ok().body(createdDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<usuarioDto> update(@PathVariable Long id, @RequestBody usuarioDto dto) {
        usuarioDto createdDto = usuarioservices.update(dto, id);
        return ResponseEntity.ok().body(createdDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<usuarioDto> delectar(@PathVariable Long id) {
        usuarioservices.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
