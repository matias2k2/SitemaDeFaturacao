package tinario9945.gmail.com.SistemaFauracao.Services;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import tinario9945.gmail.com.SistemaFauracao.DTO.FaturaDto;
import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;
import tinario9945.gmail.com.SistemaFauracao.Repository.FaturaRepository;

@Service
public class FaturaServices {
    @Autowired
    private FaturaRepository faturasrepository;

    @Transactional
    public List<FaturaDto> findAll() {
        List<Fatura> result = faturasrepository.findAll();
        return result.stream().map(FaturaDto::new).collect(Collectors.toList());
    }

    @Transactional
    public FaturaDto findById(Long id) {
        Fatura fatura = faturasrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fatura não encontrada com id: " + id));
        return new FaturaDto(fatura);
    }
     
    public FaturaDto insert(FaturaDto dto)
    {   
        Fatura faturas = new Fatura();
        faturas.setData_emissao(dto.getData_emissao());
        faturas.setQuantidade(dto.getQuantidade());
        faturas.setValorTotal(dto.getValorTotal());
        faturas =faturasrepository.save(faturas);
        return new FaturaDto(faturas);
    }

    @Transactional
    public FaturaDto update(Long id, FaturaDto dto) {
        Fatura fatura = faturasrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fatura não encontrada com id: " + id));

        fatura.setData_emissao(dto.getData_emissao());
        fatura.setQuantidade(dto.getQuantidade());
        fatura.setValorTotal(dto.getValorTotal());

        fatura = faturasrepository.save(fatura);
        return new FaturaDto(fatura);
    }


    @Transactional
    public FaturaDto deletar(Long id) {
        try {

            faturasrepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }

        return null;
    }

}
