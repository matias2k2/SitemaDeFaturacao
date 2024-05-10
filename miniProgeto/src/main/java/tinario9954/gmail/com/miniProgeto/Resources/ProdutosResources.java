package tinario9954.gmail.com.miniProgeto.Resources;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tinario9954.gmail.com.miniProgeto.entities.Produtos;

@RestController
@RequestMapping(value = "/Produtos")
public class ProdutosResources {

     public ResponseEntity<List<Produtos>> findAll() {
        List<Produtos> listProdutos = new ArrayList<>();

        listProdutos.add(new Produtos(1, "bolachas", "maria quetar", 25000));
        listProdutos.add(new Produtos(2, "Gasossa", "Coca cola", 25000));
        return ResponseEntity.ok().body(listProdutos);
     }
    
}
