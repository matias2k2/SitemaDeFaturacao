package tinario9945.gmail.com.SistemaFauracao.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import tinario9945.gmail.com.SistemaFauracao.DTO.ClienteDto;
import tinario9945.gmail.com.SistemaFauracao.DTO.usuarioDto;
import tinario9945.gmail.com.SistemaFauracao.Models.Cliente;
import tinario9945.gmail.com.SistemaFauracao.Models.usuario;
import tinario9945.gmail.com.SistemaFauracao.Repository.usuarioRepository;

@Service
public class usuarioServices {
    @Autowired
    private usuarioRepository usuariorepository;

    public List<usuarioDto> findAll() {
        List<usuario> result = usuariorepository.findAll();
        List<usuarioDto> dto = result.stream().map(x -> new usuarioDto(x)).collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public usuarioDto findById(Long id) {
        Optional<usuario> obj = usuariorepository.findById(id);
        usuario entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new usuarioDto(entity);
    }

    @Transactional
    public usuarioDto insert(usuarioDto dto) {
        usuario entity = new usuario();
        entity.setNome(dto.getNome());
        entity.setSenha(dto.getSenha());
        entity.setEmail(dto.getEmail());
        entity = usuariorepository.save(entity);
        return new usuarioDto(entity);
    }

    @Transactional
    public usuarioDto update(usuarioDto dto, Long id) {
        try {
            // Tenta encontrar o cliente pelo ID
            usuario entity = usuariorepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

            // Atualiza os campos do cliente
            entity.setNome(dto.getNome());
            entity.setSenha(dto.getSenha());
            entity.setEmail(dto.getEmail());
            // Salva as alterações no banco de dados
            entity = usuariorepository.save(entity);

            // Retorna o cliente atualizado como um ClienteDto
            return new usuarioDto(entity);

        } catch (EntityNotFoundException e) {
            // Lança uma exceção personalizada ou trata o erro de outra forma
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    @Transactional
    public ClienteDto deletar(Long id) {
        try {

            usuariorepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }

        return null;
    }


    
}
