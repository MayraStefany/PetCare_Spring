package tr.com.jowl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tr.com.jowl.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("from Cliente d where d.nombreCliente like %:nombreCliente%")
	List<Cliente> findByNombreCliente(String nombreCliente);

	@Query("select count(d.dniCliente) from Cliente d where d.dniCliente =:dni")
	public int contarDni(@Param("dni") String dni);
}
