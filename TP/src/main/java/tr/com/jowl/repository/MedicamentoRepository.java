package tr.com.jowl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.com.jowl.entity.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

	@Query("from Medicamento d where d.nombreMedicamento like %:nombre%")
	List<Medicamento> findByNombreMedicamento(String nombre);

	@Query("from Medicamento d where d.tipoMedicamento like %:tipo%")
	List<Medicamento> findByTipoMedicamento(String tipo);
}
