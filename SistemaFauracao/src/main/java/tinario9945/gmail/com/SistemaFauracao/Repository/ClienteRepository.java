package tinario9945.gmail.com.SistemaFauracao.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tinario9945.gmail.com.SistemaFauracao.Models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{
    @Query("SELECT C FROM Cliente C WHERE C.nome= :nome ")
    Optional<Cliente> findByName(@Param("nome") String name);
}
