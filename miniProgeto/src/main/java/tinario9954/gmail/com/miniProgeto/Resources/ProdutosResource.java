package tinario9954.gmail.com.miniProgeto.Resources;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value =  "/Produtos")
public class ProdutosResource {
    

    //Cria o nosso primero end pointe
    /* 
    @GetMapping
    public ResponseEntity<List<Produtos> > findAll()
    {
        List<Produtos> listaProdutos = new ArrayList<>();
        listaProdutos.add(new Produtos(1, "Coca cola", "Gasosa", 150));
        listaProdutos.add(new Produtos(1, "Coca cola", "Gasosa", 150));
        return ResponseEntity.ok().body(listaProdutos);
    }
    */
}
