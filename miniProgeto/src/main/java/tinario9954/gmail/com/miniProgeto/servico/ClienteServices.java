package tinario9954.gmail.com.miniProgeto.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tinario9954.gmail.com.miniProgeto.DTO.ClienteDto;
import tinario9954.gmail.com.miniProgeto.entities.Cliente;
import tinario9954.gmail.com.miniProgeto.repository.ClienteRepository;

public class ClienteServices {
    
    @Autowired
    private ClienteRepository clientesrepository;

    public List<ClienteDto> findAll()
    {
        List<Cliente> result = clientesrepository.findAll();
        List<ClienteDto> dto = result.stream().map(x-> new ClienteDto(x)).toList();
        
        return dto;
    }
}
