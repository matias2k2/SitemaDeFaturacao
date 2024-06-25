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

import tinario9945.gmail.com.SistemaFauracao.DTO.MarcasDto;

import tinario9945.gmail.com.SistemaFauracao.Models.Marcas;
import tinario9945.gmail.com.SistemaFauracao.Repository.MarcasRepository;

@Service
public class MarcasServices {

    @Autowired
    private MarcasRepository repository;

    public List<MarcasDto> findAll() {
        List<Marcas> obj = repository.findAll();
        List<MarcasDto> dto = obj.stream().map(x -> new MarcasDto(x)).collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public MarcasDto findById(Integer id) {
        Optional<Marcas> obj = repository.findById(id);
        Marcas entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new MarcasDto(entity);
    }

    @Transactional
    public MarcasDto insert(MarcasDto dto) {
        Marcas entity = new Marcas();
        entity.setNome(dto.getNome());

        entity = repository.save(entity);
        return new MarcasDto(entity);
    }

    @Transactional
    public MarcasDto update(MarcasDto dto, Integer id) {
        try {
            // Tenta encontrar o cliente pelo ID
            Marcas entity = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

            // Atualiza os campos do cliente
            entity.setNome(dto.getNome());

            // Salva as alterações no banco de dados
            entity = repository.save(entity);

            // Retorna o cliente atualizado como um ClienteDto
            return new MarcasDto(entity);

        } catch (EntityNotFoundException e) {
            // Lança uma exceção personalizada ou trata o erro de outra forma
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    @Transactional
    public MarcasDto deletar(Integer id) {
        try {

            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }

        return null;

    }

    public MarcasDto findByName(String name) {
        Optional<Marcas> obj = repository.findByNome(name);
        Marcas entity = obj.orElseThrow(() -> new EntityNotFoundException("Marca não encontrada"));
        return new MarcasDto(entity);
    }

}
