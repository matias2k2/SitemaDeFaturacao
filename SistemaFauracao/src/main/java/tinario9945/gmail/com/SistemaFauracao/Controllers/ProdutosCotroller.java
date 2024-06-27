package tinario9945.gmail.com.SistemaFauracao.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tinario9945.gmail.com.SistemaFauracao.DTO.ProdutodoDTO;
import tinario9945.gmail.com.SistemaFauracao.DTO.usuarioDto;
import tinario9945.gmail.com.SistemaFauracao.Services.ProdutosSevices;

@RestController
@RequestMapping("/produtos")
public class ProdutosCotroller {
    
    @Autowired
    private ProdutosSevices produtoservices;

    @GetMapping
    public List<ProdutodoDTO> findAll() {
        List<ProdutodoDTO> result = produtoservices.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutodoDTO> findById(@PathVariable Integer  id) {
        ProdutodoDTO resl = produtoservices.findById(id);
        return ResponseEntity.ok().body(resl);
    }

    @PostMapping
    public ResponseEntity<ProdutodoDTO> insert(@RequestBody ProdutodoDTO dto) {
        ProdutodoDTO createdDto = produtoservices.insert(dto);
        return ResponseEntity.ok().body(createdDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutodoDTO> update(@PathVariable Integer  id, @RequestBody ProdutodoDTO dto) {
        ProdutodoDTO createdDto = produtoservices.update(dto, id);
        return ResponseEntity.ok().body(createdDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProdutodoDTO> delectar(@PathVariable Integer  id) {
        produtoservices.deletar(id);
        return ResponseEntity.noContent().build();
    }
    /* 
    @GetMapping("/name/{name}")
    public ResponseEntity<ProdutodoDTO> findByName(@PathVariable String name) {
        ProdutodoDTO dto = produtoservices.findByName(name);
        return ResponseEntity.ok(dto);
    }
    */
    
}
