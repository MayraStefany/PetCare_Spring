package tr.com.jowl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.com.jowl.entity.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {

	@Query("from Mascota a where a.nombreMascota like %:nombreMascota%")
	List<Mascota> findByNombreMascota(String nombreMascota);

	@Query("from Mascota a where a.razaMascota like %:razaMascota%")
	List<Mascota> findByRazaMascota(String razaMascota);

}
