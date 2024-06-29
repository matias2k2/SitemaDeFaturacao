package tinario9945.gmail.com.SistemaFauracao.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tinario9945.gmail.com.SistemaFauracao.Models.usuario;

@Repository
public interface usuarioRepository extends JpaRepository<usuario,Integer> {
    @Query("SELECT u FROM usuario u WHERE u.nome  = :nome ")
    Optional<usuario> findByNome(@Param("nome") String nome);

    
}
