package tinario9945.gmail.com.SistemaFauracao.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tinario9945.gmail.com.SistemaFauracao.Models.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos,Long>  {
    //Optional<Produtos> findByName(String name);
}
