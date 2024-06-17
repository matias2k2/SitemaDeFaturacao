package tinario9945.gmail.com.SistemaFauracao.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tinario9945.gmail.com.SistemaFauracao.DTO.ClienteDto;
import tinario9945.gmail.com.SistemaFauracao.Models.Cliente;
import tinario9945.gmail.com.SistemaFauracao.Repository.ClienteRepository;


@Service
public class ClienteServices {
    @Autowired
    private ClienteRepository clientesrepository;

    public List<ClienteDto> findAll() {
        List<Cliente> result = clientesrepository.findAll();
        List<ClienteDto> dto = result.stream().map(x -> new ClienteDto(x)).toList();

        return dto;
    }
}
