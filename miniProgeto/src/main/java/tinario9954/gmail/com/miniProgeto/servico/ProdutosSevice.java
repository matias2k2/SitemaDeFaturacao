package tinario9954.gmail.com.miniProgeto.servico;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.GetExchange;

import tinario9954.gmail.com.miniProgeto.entities.Produtos;
import tinario9954.gmail.com.miniProgeto.repository.ProdutosRepository;

@Service
public class ProdutosSevice {

    // essa classe e onde finca o nosso sql
    @Autowired
    private ProdutosRepository repositorio;


    @GetMapping
    public List<Produtos> findAll()
    {
        return repositorio.findAll();
    }
    
}
