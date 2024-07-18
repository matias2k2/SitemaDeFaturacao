package tinario9945.gmail.com.SistemaFauracao.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import tinario9945.gmail.com.SistemaFauracao.DTO.FaturaDto;
import tinario9945.gmail.com.SistemaFauracao.DTO.ItemFaturaDto;
import tinario9945.gmail.com.SistemaFauracao.Models.Catigoria;
import tinario9945.gmail.com.SistemaFauracao.Models.Cliente;
import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;
import tinario9945.gmail.com.SistemaFauracao.Models.ItemFatura;
import tinario9945.gmail.com.SistemaFauracao.Models.Marcas;
import tinario9945.gmail.com.SistemaFauracao.Models.Produtos;
import tinario9945.gmail.com.SistemaFauracao.Models.usuario;
import tinario9945.gmail.com.SistemaFauracao.Repository.CatigoriaRepository;
import tinario9945.gmail.com.SistemaFauracao.Repository.ClienteRepository;
import tinario9945.gmail.com.SistemaFauracao.Repository.FaturaRepository;

import tinario9945.gmail.com.SistemaFauracao.Repository.MarcasRepository;
import tinario9945.gmail.com.SistemaFauracao.Repository.ProdutosRepository;
import tinario9945.gmail.com.SistemaFauracao.Repository.usuarioRepository;

@Service
public class FaturaServices {

       @Autowired
    private FaturaRepository faturasRepository;

    @Autowired
    private CatigoriaRepository categoriaRepository;

    @Autowired
    private MarcasRepository marcasRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private usuarioRepository usuarioRepository;

    @Transactional
    public List<FaturaDto> findAll() {
        List<Fatura> result = faturasRepository.findAll();
        return result.stream().map(FaturaDto::new).collect(Collectors.toList());
    }

    @Transactional
    public FaturaDto findById(Integer id) {
        Fatura fatura = faturasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fatura não encontrada com id: " + id));
        return new FaturaDto(fatura);
    }

    @Transactional
    public FaturaDto insert(FaturaDto dto) {
            Fatura fatura = new Fatura();

            Cliente cliente = clienteRepository.findById(dto.getCliente().getId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                            "Cliente não encontrado com ID: " + dto.getCliente().getId()));

            usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                            "Usuário não encontrado com ID: " + dto.getUsuarioId()));

            fatura.setCliente(cliente);
            fatura.setUsuario(usuario);

            if (dto.getItens() == null || dto.getItens().isEmpty()) {
                    throw new IllegalArgumentException("A fatura deve conter pelo menos um item");
            }

            for (ItemFaturaDto itemDto : dto.getItens()) {
                    ItemFatura item = criarItemFatura(itemDto, fatura);
                    fatura.adicionarItem(item);
            }

            fatura.calcularValorTotal();
            fatura = faturasRepository.save(fatura);

            return new FaturaDto(fatura);
    }

    private ItemFatura criarItemFatura(ItemFaturaDto itemDto, Fatura fatura) {
            ItemFatura item = new ItemFatura();
            item.setQuantidade(itemDto.getQuantidade());

            Produtos produto = produtosRepository.findById(itemDto.getProdutoId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                            "Produto não encontrado com ID: " + itemDto.getProdutoId()));

            Catigoria categoria = categoriaRepository.findById(itemDto.getCategoriaId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                            "Categoria não encontrada com ID: " + itemDto.getCategoriaId()));

            Marcas marca = marcasRepository.findById(itemDto.getMarcaId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                            "Marca não encontrada com ID: " + itemDto.getMarcaId()));

            item.setProduto(produto);
            item.setCategoria(categoria);
            item.setMarca(marca);
            item.setFatura(fatura);

            return item;
    }
    @Transactional
    public FaturaDto update(Integer id, FaturaDto dto) {
        Fatura fatura = faturasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fatura não encontrada com id: " + id));

        Cliente cliente = clienteRepository.findById(dto.getCliente().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + dto.getCliente().getId()));

        usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + dto.getUsuarioId()));

        fatura.setCliente(cliente);
        fatura.setUsuario(usuario);

        if (dto.getItens() == null || dto.getItens().isEmpty()) {
            throw new IllegalArgumentException("A fatura deve conter pelo menos um item");
        }

        // Atualiza os itens existentes e adiciona novos
        Set<Integer> updatedItemIds = new HashSet<>();
        for (ItemFaturaDto itemDto : dto.getItens()) {
            if (itemDto.getId() != null) {
                ItemFatura existingItem = fatura.getItens().stream()
                        .filter(item -> item.getId().equals(itemDto.getId()))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("Item não encontrado na fatura: " + itemDto.getId()));
                atualizarItemFatura(existingItem, itemDto);
                updatedItemIds.add(existingItem.getId());
            } else {
                ItemFatura newItem = criarItemFatura(itemDto, fatura);
                fatura.adicionarItem(newItem);
            }
        }

        // Remove itens que não estão mais presentes
        fatura.getItens().removeIf(item -> !updatedItemIds.contains(item.getId()));

        fatura.calcularValorTotal();
        fatura = faturasRepository.save(fatura);

        return new FaturaDto(fatura);
    }

    @Transactional
    public void deletar(Integer id) {
        try {
            faturasRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Fatura não encontrada com id: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir a fatura devido a restrições de integridade");
        }
    }

   

    private void atualizarItemFatura(ItemFatura item, ItemFaturaDto itemDto) {
        item.setQuantidade(itemDto.getQuantidade());

        if (!item.getProduto().getId().equals(itemDto.getProdutoId())) {
            Produtos produto = produtosRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + itemDto.getProdutoId()));
            item.setProduto(produto);
        }

        if (!item.getCategoria().getId().equals(itemDto.getCategoriaId())) {
            Catigoria categoria = categoriaRepository.findById(itemDto.getCategoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + itemDto.getCategoriaId()));
            item.setCategoria(categoria);
        }

        if (!item.getMarca().getId().equals(itemDto.getMarcaId())) {
            Marcas marca = marcasRepository.findById(itemDto.getMarcaId())
                    .orElseThrow(() -> new EntityNotFoundException("Marca não encontrada com ID: " + itemDto.getMarcaId()));
            item.setMarca(marca);
        }
    }
}
