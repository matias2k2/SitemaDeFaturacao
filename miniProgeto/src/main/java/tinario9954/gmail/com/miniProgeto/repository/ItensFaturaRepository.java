package tinario9954.gmail.com.miniProgeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tinario9954.gmail.com.miniProgeto.entities.ItensFatura;

@Repository
public interface ItensFaturaRepository extends JpaRepository<ItensFatura,Integer> {
    
}
