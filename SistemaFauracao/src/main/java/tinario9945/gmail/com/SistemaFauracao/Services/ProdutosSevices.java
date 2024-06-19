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
import tinario9945.gmail.com.SistemaFauracao.DTO.ProdutodoDTO;
import tinario9945.gmail.com.SistemaFauracao.Models.Produtos;
import tinario9945.gmail.com.SistemaFauracao.Repository.ProdutosRepository;

@Service
public class ProdutosSevices {

    @Autowired
    private ProdutosRepository produtosrepository;

    public List<ProdutodoDTO> findAll() {
        List<Produtos> result = produtosrepository.findAll();
        List<ProdutodoDTO> dto = result.stream().map(x -> new ProdutodoDTO(x)).collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public ProdutodoDTO findById(Long id) {
        Optional<Produtos> obj = produtosrepository.findById(id);
        Produtos entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new ProdutodoDTO(entity);
    }

    @Transactional
    public ProdutodoDTO insert(ProdutodoDTO dto) {
        Produtos entity = new Produtos();
        entity.setNameProdutos(dto.getNameProdutos());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity = produtosrepository.save(entity);
        return new ProdutodoDTO(entity);
    }

    @Transactional
    public ProdutodoDTO update(ProdutodoDTO dto, Long id) {
        try {
            // Tenta encontrar o cliente pelo ID
            Produtos entity = produtosrepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

            // Atualiza os campos do cliente
            entity.setNameProdutos(dto.getNameProdutos());
            entity.setDescricao(dto.getDescricao());
            entity.setPreco(dto.getPreco());
            // Salva as alterações no banco de dados
            entity = produtosrepository.save(entity);

            // Retorna o cliente atualizado como um ClienteDto
            return new ProdutodoDTO(entity);

        } catch (EntityNotFoundException e) {
            // Lança uma exceção personalizada ou trata o erro de outra forma
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    @Transactional
    public ProdutodoDTO deletar(Long id) {
        try {

            produtosrepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            // Lança uma exceção personalizada se o cliente não for encontrado
            throw new UnsupportedOperationException("Unimplemented method 'deletar'");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integridade inválida");
        }

        return null;
    }
}
