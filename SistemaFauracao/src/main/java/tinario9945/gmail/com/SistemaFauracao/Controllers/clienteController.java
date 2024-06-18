package tinario9945.gmail.com.SistemaFauracao.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tinario9945.gmail.com.SistemaFauracao.DTO.ClienteDto;
import tinario9945.gmail.com.SistemaFauracao.Services.ClienteServices;

@RestController
@RequestMapping(value = "/clientes")
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
        dto = clienteserve.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getCliente_id())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
