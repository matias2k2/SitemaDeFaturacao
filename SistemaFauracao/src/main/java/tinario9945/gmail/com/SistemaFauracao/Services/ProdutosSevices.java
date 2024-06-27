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
import tinario9945.gmail.com.SistemaFauracao.Models.Catigoria;
import tinario9945.gmail.com.SistemaFauracao.Models.Marcas;
import tinario9945.gmail.com.SistemaFauracao.Models.Produtos;
import tinario9945.gmail.com.SistemaFauracao.Repository.CatigoriaRepository;
import tinario9945.gmail.com.SistemaFauracao.Repository.MarcasRepository;
import tinario9945.gmail.com.SistemaFauracao.Repository.ProdutosRepository;

@Service
public class ProdutosSevices {

    @Autowired
    private ProdutosRepository produtosrepository;
    @Autowired
    private CatigoriaRepository repositoryCategoria;
    @Autowired
    private MarcasRepository repositoryMarcas;

    public List<ProdutodoDTO> findAll() {
        List<Produtos> result = produtosrepository.findAll();
        List<ProdutodoDTO> dto = result.stream().map(x -> new ProdutodoDTO(x)).collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public ProdutodoDTO findById(Integer id) {
        Optional<Produtos> obj = produtosrepository.findById(id);
        Produtos entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade nao encontrada"));
        return new ProdutodoDTO(entity);
    }

    @Transactional
    public ProdutodoDTO insert(ProdutodoDTO dto) {
        Marcas marcas = new Marcas();
        Produtos entity = new Produtos();
        Catigoria categoria = new Catigoria();

        entity.setNomeProduto(dto.getNomeProduto());

        entity.setPreco(dto.getPreco());
        if (dto != null) {
            // Buscar as entidades de Marca e Categoria pelo ID
            marcas = repositoryMarcas.findById(dto.getMarcaId())
                    .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
            categoria = repositoryCategoria.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

            entity.setMarca(marcas);
            entity.setCategoria(categoria);

        }
        entity = produtosrepository.save(entity);
        return new ProdutodoDTO(entity);
    }

    @Transactional
    public ProdutodoDTO update(ProdutodoDTO dto, Integer id) {
        try {
            Produtos entity = produtosrepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));

            entity.setNomeProduto(dto.getNomeProduto());
            entity.setPreco(dto.getPreco());

            entity = produtosrepository.save(entity);
            return new ProdutodoDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    @Transactional
    public ProdutodoDTO deletar(Integer id) {
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
