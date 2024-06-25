package tinario9945.gmail.com.SistemaFauracao.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tinario9945.gmail.com.SistemaFauracao.Models.Marcas;

@Repository
public interface MarcasRepository extends JpaRepository<Marcas,Integer>  {
    Optional<Marcas> findByNome(String nome);
    
}