package tinario9945.gmail.com.SistemaFauracao.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
