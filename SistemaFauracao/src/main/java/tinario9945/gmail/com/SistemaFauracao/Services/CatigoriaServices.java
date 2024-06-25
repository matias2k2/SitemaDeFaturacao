package tinario9945.gmail.com.SistemaFauracao.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import tinario9945.gmail.com.SistemaFauracao.DTO.CatigoriaDto;
import tinario9945.gmail.com.SistemaFauracao.Models.Catigoria;
import tinario9945.gmail.com.SistemaFauracao.Models.Marcas;
import tinario9945.gmail.com.SistemaFauracao.Repository.CatigoriaRepository;
import tinario9945.gmail.com.SistemaFauracao.Repository.MarcasRepository;

@Service
public class CatigoriaServices {
    @Autowired
    private CatigoriaRepository repository;
    @Autowired
    private MarcasRepository marcasRepository;

    // Retomando a lista d
    public List<CatigoriaDto> findAll() {
        List<Catigoria> resulatado = repository.findAll();
        List<CatigoriaDto> dto = resulatado.stream().map(x -> new CatigoriaDto(x)).collect(Collectors.toList());
        return dto;

    }

    @Transactional
    public CatigoriaDto insert(CatigoriaDto dto) {
        Catigoria entity = new Catigoria();
        entity.setName(dto.getName());

        Optional<Marcas> marcasOpt = marcasRepository.findById(dto.getMarcasId());

        if (marcasOpt.isPresent()) {
            entity.setMarcas(marcasOpt.get());
        } else {
            throw new EntityNotFoundException("Marca não encontrada com o ID: " + dto.getMarcasId());
        }

        try {
            entity = repository.save(entity);
            return new CatigoriaDto(entity);
        } catch (DataIntegrityViolationException e) {
            // Tratar exceção de violação de integridade (ex: registro duplicado)
            throw new IllegalArgumentException("Nome de categoria duplicado: " + dto.getName());
        }
    }
    /*
    @Transactional
    public CatigoriaDto findByName(String name) {
        Catigoria entity = repository.findByName(name)
            .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
        return new CatigoriaDto(entity);
    }
     */

}
