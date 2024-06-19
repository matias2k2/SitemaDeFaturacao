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
import tinario9945.gmail.com.SistemaFauracao.DTO.ItensFaturaDto;
import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;
import tinario9945.gmail.com.SistemaFauracao.Models.ItensFatura;
import tinario9945.gmail.com.SistemaFauracao.Repository.ItensFaturaRepository;

@Service
public class ItensFaturaServices {
    @Autowired
    private ItensFaturaRepository ItensFaturass;

    @Transactional
    public List<ItensFaturaDto> findAll() {
        List<ItensFatura> result = ItensFaturass.findAll();
        return result.stream().map(ItensFaturaDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ItensFaturaDto findById(int id) {
        ItensFatura itensfatura = ItensFaturass.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ItesFatura não encontrada com id: " + id));
        return new ItensFaturaDto(itensfatura);
    }

    public ItensFaturaDto insert(ItensFaturaDto dto) {
        ItensFatura itemdfaturas = new ItensFatura();
        itemdfaturas.setPreco_unitari(dto.getPreco_unitari());
        itemdfaturas.setQuantidade(dto.getQuantidade());
        itemdfaturas.setSubtotal(dto.getSubtotal());
        itemdfaturas = ItensFaturass.save(itemdfaturas);
        return new ItensFaturaDto(itemdfaturas);
    }

    @Transactional
    public ItensFaturaDto update(int id, ItensFaturaDto dto) {
        ItensFatura itemdfaturas = ItensFaturass.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ItesFatura não encontrada com id: " + id));

        itemdfaturas.setPreco_unitari(dto.getPreco_unitari());
        itemdfaturas.setQuantidade(dto.getQuantidade());
        itemdfaturas.setSubtotal(dto.getSubtotal());

        itemdfaturas = ItensFaturass.save(itemdfaturas);
        return new ItensFaturaDto(itemdfaturas);
    }

    @Transactional
    public FaturaDto deletar(int id) {
        try {

            ItensFaturass.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }

        return null;
    }
}
