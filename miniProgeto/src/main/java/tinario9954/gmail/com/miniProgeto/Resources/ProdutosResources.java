package tinario9954.gmail.com.miniProgeto.Resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tinario9954.gmail.com.miniProgeto.entities.Produtos;
import tinario9954.gmail.com.miniProgeto.servico.ProdutosSevice;

@RestController
@RequestMapping(value = "/Produtos")
public class ProdutosResources {


   @Autowired
   private ProdutosSevice service;
   
   @GetMapping
   public ResponseEntity<List<Produtos>> findAll() {
      //ele vai receber tudo que vem  do banco de dados
      List<Produtos> listProdutos = service.findAll();

      return ResponseEntity.ok().body(listProdutos);
   }

}
