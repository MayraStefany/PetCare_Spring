package tr.com.jowl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.com.jowl.entity.DetalleReceta;
import tr.com.jowl.entity.Medico;

@Repository
public interface DetalleRepository extends JpaRepository<DetalleReceta, Integer> {

	@Query("from DetalleReceta c where c.recetaDetalle.idReceta = :idReceta")
	List<Medico> buscarporidRecetaDetalle(int idReceta);
}
