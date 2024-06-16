package tinario9954.gmail.com.miniProgeto.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tinario9954.gmail.com.miniProgeto.DTO.ClienteDto;
import tinario9954.gmail.com.miniProgeto.servico.ClienteServices;

@RestController
@RequestMapping(value = "/clientes")
public class clienteController {
    
    @Autowired
    private ClienteServices clienteserve;


    @GetMapping("/")
    public List<ClienteDto> findAll()
    {
        List<ClienteDto> result = clienteserve.findAll();
        return result;
    }

}
