package tinario9945.gmail.com.SistemaFauracao.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tinario9945.gmail.com.SistemaFauracao.DTO.ClienteDto;
import tinario9945.gmail.com.SistemaFauracao.Services.ClienteServices;

@RestController
@RequestMapping("/clientes")
public class clienteController {
    @Autowired
    private ClienteServices clienteserve;

    @GetMapping
    public List<ClienteDto> findAll() {
        List<ClienteDto> result = clienteserve.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable Long id) {
        ClienteDto resl = clienteserve.findById(id);
        return ResponseEntity.ok().body(resl);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> insert(@RequestBody ClienteDto dto) {
        ClienteDto createdDto = clienteserve.insert(dto);
        return ResponseEntity.ok().body(createdDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Long id, @RequestBody ClienteDto dto ) {
        ClienteDto createdDto = clienteserve.update(dto,id);
        return ResponseEntity.ok().body(createdDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> delectar(@PathVariable Long id) {
         clienteserve.deletar(id);
        return ResponseEntity.noContent().build();
    }
    /* 
    @GetMapping("/name/{name}")
    public ResponseEntity<ClienteDto> findByName(@PathVariable String name) {
        ClienteDto dto = clienteserve.findByName(name);
        return ResponseEntity.ok(dto);
    }
    */

}
