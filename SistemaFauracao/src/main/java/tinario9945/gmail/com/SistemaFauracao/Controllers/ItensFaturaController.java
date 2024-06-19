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

import tinario9945.gmail.com.SistemaFauracao.DTO.ClienteDto;
import tinario9945.gmail.com.SistemaFauracao.DTO.ItensFaturaDto;
import tinario9945.gmail.com.SistemaFauracao.Services.ItensFaturaServices;

@RestController
@RequestMapping("/itensFaturas")
public class ItensFaturaController {
    @Autowired
    private  ItensFaturaServices services;

     @GetMapping
    public List<ItensFaturaDto> findAll() {
        List<ItensFaturaDto> result = services.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItensFaturaDto> findById(@PathVariable int id) {
        ItensFaturaDto resl = services.findById(id);
        return ResponseEntity.ok().body(resl);
    }

    @PostMapping
    public ResponseEntity<ItensFaturaDto> insert(@RequestBody ItensFaturaDto dto) {
        ItensFaturaDto createdDto = services.insert(dto);
        return ResponseEntity.ok().body(createdDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ItensFaturaDto> update(@PathVariable int id, @RequestBody ItensFaturaDto dto ) {
        ItensFaturaDto createdDto = services.update(id,dto);
        return ResponseEntity.ok().body(createdDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ItensFaturaDto> delectar(@PathVariable int id) {
        services.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
