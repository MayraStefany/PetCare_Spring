package tr.com.jowl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.jowl.entity.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

	@Query("from Medico d where d.nombreMedico like %:nombreMedico%")
	List<Medico> findByNombreMedico(String nombreMedico);

	@Query("select count(d.dniMedico) from Medico d where d.dniMedico =:dni")
	public int contarDni(@Param("dni") String dni);

	@Query("from Medico c where c.veterinariaMedico.idVeterinaria = :idVeterinaria")
	List<Medico> buscarporidVeterinariaMedico(int idVeterinaria);
}
