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
        entity.setNome(dto.getNome());

        // Busca a marca associada pelo ID
        Marcas marca = marcasRepository.findById(dto.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada com ID: " + dto.getMarcaId()));

        entity.setMarca(marca); // Define a marca na entidade Catigoria

        // Salva a entidade no repositório
        entity = repository.save(entity);

        return new CatigoriaDto(entity);
    }

}
