package tinario9945.gmail.com.SistemaFauracao.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tinario9945.gmail.com.SistemaFauracao.Models.Fatura;
@Repository
public interface FaturaRepository extends JpaRepository<Fatura,Long>{
    
}