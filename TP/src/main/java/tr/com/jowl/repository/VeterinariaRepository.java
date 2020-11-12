package tr.com.jowl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.com.jowl.entity.Veterinaria;

@Repository
public interface VeterinariaRepository extends JpaRepository<Veterinaria, Integer> {

	@Query("from Veterinaria c where c.nombreVeterinaria like %:nombreVeterinaria%")
	List<Veterinaria> findByNombreVeterinaria(String nombreVeterinaria);

	@Query("from Veterinaria c where c.distritoVeterinaria like %:distritoVeterinaria%")
	List<Veterinaria> findByDistritoVeterinaria(String distritoVeterinaria);

	@Query("from Veterinaria c where c.horarioVeterinaria like %:horarioVeterinaria%")
	List<Veterinaria> findByHorarioVeterinaria(String horarioVeterinaria);

}
