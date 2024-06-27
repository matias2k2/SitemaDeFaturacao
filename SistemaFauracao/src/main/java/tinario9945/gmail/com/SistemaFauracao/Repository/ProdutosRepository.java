package tinario9945.gmail.com.SistemaFauracao.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tinario9945.gmail.com.SistemaFauracao.Models.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos,Integer>  {
    //@Query("SELECT u FROM usuario u WHERE u.nome = :nome ")
    @Query("SELECT P FROM Produtos P WHERE P.nomeProduto = :nome")
    Optional<Produtos> findByName(@Param("nome") String nome);
}
