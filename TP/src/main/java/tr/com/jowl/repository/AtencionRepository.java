package tr.com.jowl.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.jowl.entity.Atencion;

@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Integer> {

}
