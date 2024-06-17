package tinario9945.gmail.com.SistemaFauracao.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tinario9945.gmail.com.SistemaFauracao.Repository.ProdutosRepository;
@Service
public class ProdutosSevices {
        
    @Autowired
    private ProdutosRepository produtosrepository;
}
