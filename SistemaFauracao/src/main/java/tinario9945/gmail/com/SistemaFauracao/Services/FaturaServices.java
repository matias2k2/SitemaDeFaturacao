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
import tinario9945.gmail.com.SistemaFauracao.Models.Catigoria;
import tinario9945.gmail.com.SistemaFauracao.Models.Cliente;
import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;
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
    private FaturaRepository faturasrepository;
    @Autowired
    private CatigoriaRepository repositoryCategoria;
    @Autowired
    private MarcasRepository marcasRepository;
    @Autowired
    private ProdutosRepository produtosrepository;
   
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private usuarioRepository usuariorepository;
    @Transactional
    public List<FaturaDto> findAll() {
        List<Fatura> result = faturasrepository.findAll();
        return result.stream().map(FaturaDto::new).collect(Collectors.toList());
    }

    @Transactional
    public FaturaDto findById(Integer id) {
        Fatura fatura = faturasrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fatura não encontrada com id: " + id));
        return new FaturaDto(fatura);
    }

   
    @Transactional
    public FaturaDto insert(FaturaDto dto) {
        Fatura fatura = new Fatura();
        System.out.println("Dados recebidos para a fatura:");
        System.out.println("Quantidade: " + dto.getQuantidade());
        System.out.println("Valor Total: " + dto.getValorTotal());
        System.out.println("Produto ID: " + dto.getProdutoId());
        System.out.println("Cliente ID: " + dto.getClienteId());
        System.out.println("Categoria ID: " + dto.getCategoriaId());
        System.out.println("Marca ID: " + dto.getMarcaId());
        System.out.println("Usuário ID: " + dto.getUsuarioId());

        // Configura os dados da fatura
        
        fatura.setQuantidade(dto.getQuantidade());
        fatura.setValorTotal(dto.getValorTotal());

        // Busca as entidades associadas (marca, categoria, produto, cliente, etc.) pelo
        // ID
        Marcas marca = marcasRepository.findById(dto.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada com ID: " + dto.getMarcaId()));

        Catigoria categoria = repositoryCategoria.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + dto.getCategoriaId()));

        Produtos produtos = produtosrepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + dto.getProdutoId()));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + dto.getClienteId()));

        usuario usuario = usuariorepository.findById(dto.getUsuarioId())
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + dto.getUsuarioId()));
        

        // Associa as entidades à fatura
        fatura.setMarca(marca);
        fatura.setCategoria(categoria);
        fatura.setProduto(produtos);
        fatura.setCliente(cliente);
        fatura.setUsuario(usuario);

        // Salva a fatura no banco de dados
        fatura = faturasrepository.save(fatura);

        // Retorna o DTO da fatura salva
        return new FaturaDto(fatura);
    }

    @Transactional
    public FaturaDto update(Integer id, FaturaDto dto) {
        Fatura fatura = faturasrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fatura não encontrada com id: " + id));

        // Atualiza os dados da fatura com base no DTO recebido
    
        fatura.setQuantidade(dto.getQuantidade());
        fatura.setValorTotal(dto.getValorTotal());

        // Busca as entidades associadas (marca, categoria, produto, cliente, etc.) pelo
        // ID
        Marcas marca = marcasRepository.findById(dto.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada com ID: " + dto.getMarcaId()));

        Catigoria categoria = repositoryCategoria.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + dto.getCategoriaId()));

        Produtos produtos = produtosrepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + dto.getProdutoId()));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + dto.getClienteId()));

        usuario usuario1 = usuariorepository.findById(dto.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + dto.getUsuarioId()));

       // Associa as entidades à fatura
       fatura.setMarca(marca);
       fatura.setCategoria(categoria);
       fatura.setProduto(produtos);
       fatura.setCliente(cliente);
       fatura.setUsuario(usuario1);


        // Salva a fatura no banco de dados
        fatura = faturasrepository.save(fatura);

        // Retorna o DTO da fatura atualizada
        return new FaturaDto(fatura);
    }

    @Transactional
    public FaturaDto deletar(Integer id) {
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
