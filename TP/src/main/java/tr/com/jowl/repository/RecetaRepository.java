package tr.com.jowl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.jowl.entity.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {

}
