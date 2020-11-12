package tr.com.jowl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.com.jowl.entity.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

	@Query("from Reserva c where c.clienteReserva.nombreCliente = :cliente")
	List<Reserva> findByNombreCliente(String cliente);

	@Query("from Reserva d where d.estadoReserva like %:estado%")
	List<Reserva> findByEstadoReserva(String estado);
}
