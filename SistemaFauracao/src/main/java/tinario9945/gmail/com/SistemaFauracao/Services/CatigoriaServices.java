package tinario9945.gmail.com.SistemaFauracao.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import tinario9945.gmail.com.SistemaFauracao.DTO.CatigoriaDto;
import tinario9945.gmail.com.SistemaFauracao.Models.Catigoria;
import tinario9945.gmail.com.SistemaFauracao.Repository.CatigoriaRepository;
@Service
public class CatigoriaServices {
    @Autowired
    private CatigoriaRepository repository;

    //Retomando a lista d
    public List<CatigoriaDto> findAll(){
        List<Catigoria> resulatado = repository.findAll();
        List<CatigoriaDto> dto = resulatado.stream().map(x -> new CatigoriaDto(x)).collect(Collectors.toList());
        return dto;
        
    }
    @Transactional
    public CatigoriaDto insert(CatigoriaDto dto) {
        Catigoria entity = new Catigoria();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CatigoriaDto(entity);
    }

}
