package tinario9945.gmail.com.SistemaFauracao.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tinario9945.gmail.com.SistemaFauracao.Models.Catigoria;


public interface CatigoriaRepository extends JpaRepository<Catigoria,Integer>{
   Optional<Catigoria> findByNome(String nome);
}
