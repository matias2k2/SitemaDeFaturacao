package tinario9945.gmail.com.SistemaFauracao.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import tinario9945.gmail.com.SistemaFauracao.DTO.ClienteDto;
import tinario9945.gmail.com.SistemaFauracao.Models.Cliente;
import tinario9945.gmail.com.SistemaFauracao.Repository.ClienteRepository;

@Service
public class ClienteServices {
    @Autowired
    private ClienteRepository clientesrepository;

    public List<ClienteDto> findAll() {
        List<Cliente> result = clientesrepository.findAll();
        List<ClienteDto> dto = result.stream().map(x -> new ClienteDto(x)).collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public ClienteDto findById(Long id) {
        Optional<Cliente> obj = clientesrepository.findById(id);
        Cliente entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new ClienteDto(entity);
    }

    @Transactional
    public ClienteDto insert(ClienteDto dto) {
        Cliente entity = new Cliente();
        entity.setNome(dto.getNome());
        entity.setEndereco(dto.getEndereco());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity = clientesrepository.save(entity);
        return new ClienteDto(entity);
    }

    @Transactional
    public ClienteDto update(ClienteDto dto, Long id) {
        try {
            // Tenta encontrar o cliente pelo ID
            Cliente entity = clientesrepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

            // Atualiza os campos do cliente
            entity.setNome(dto.getNome());
            entity.setEndereco(dto.getEndereco());
            entity.setTelefone(dto.getTelefone());
            entity.setEmail(dto.getEmail());

            // Salva as alterações no banco de dados
            entity = clientesrepository.save(entity);

            // Retorna o cliente atualizado como um ClienteDto
            return new ClienteDto(entity);

        } catch (EntityNotFoundException e) {
            // Lança uma exceção personalizada ou trata o erro de outra forma
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    @Transactional
    public ClienteDto deletar(Long id) {
        try {

            clientesrepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }

        return null;
    }
    /* 
    @Transactional
    public ClienteDto findByName(String name) {
        Cliente entity = clientesrepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
        return new ClienteDto(entity);
    }
        */

}
