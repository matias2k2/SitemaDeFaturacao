package tinario9954.gmail.com.miniProgeto.servico;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import tinario9954.gmail.com.miniProgeto.DTO.ProdutodoDTO;
import tinario9954.gmail.com.miniProgeto.entities.Produtos;
import tinario9954.gmail.com.miniProgeto.repository.ProdutosRepository;

@Service
public class ProdutosSevice {

    // essa classe e onde finca o nosso sql
    @Autowired
    private ProdutosRepository repositorio;

    public List<ProdutodoDTO > findAll()
    {
        //lista dos elemento do nosso banco de dados 
        List<Produtos> listprodutos = repositorio.findAll();

        //convete a lista em uma string 

        /*
        List<ProdutodoDTO> listaProdutosDto = new ArrayList<>();
        for( Produtos cat : listprodutos )
        {
                listaProdutosDto.add(new ProdutodoDTO(cat));
        }
        return listaProdutosDto;

        */
        
        return listprodutos.stream().map(x-> new ProdutodoDTO(x)).collect(Collectors.toList());
    
    }


    
}
