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
import tinario9945.gmail.com.SistemaFauracao.DTO.FaturaDto;
import tinario9945.gmail.com.SistemaFauracao.Services.FaturaServices;


@RestController
@RequestMapping("/fatura")
public class faturaController {
    
    @Autowired
    private FaturaServices faturaservicos;
 
    @GetMapping
    public List<FaturaDto> findAll()
    {
        List<FaturaDto> faturas = faturaservicos.findAll();
        return faturas;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<FaturaDto> findById(@PathVariable Long id)
    {
        FaturaDto result = faturaservicos.findById(id);
        return ResponseEntity.ok().body(result);
    }
    @PostMapping
    public ResponseEntity<FaturaDto> insert(@RequestBody FaturaDto dto) {
        FaturaDto createdDto = faturaservicos.insert(dto);
        return ResponseEntity.ok().body(createdDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FaturaDto> update(@PathVariable Long id, @RequestBody FaturaDto dto ) {
        FaturaDto createdDto = faturaservicos.update(id,dto);
        return ResponseEntity.ok().body(createdDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> delectar(@PathVariable Long id) {
         faturaservicos.deletar(id);
        return ResponseEntity.noContent().build();
    }

    
    
}
