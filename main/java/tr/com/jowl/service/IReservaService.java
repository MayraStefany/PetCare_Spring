package tr.com.jowl.service;

import java.util.List;
import java.util.Optional;

import tr.com.jowl.entity.Reserva;

public interface IReservaService {

	public Integer insertar(Reserva reserva);

	public void eliminar(int idReserva);

	List<Reserva> buscarNombreCliente(String cliente);

	List<Reserva> buscarEstadoReserva(String estado);

	Optional<Reserva> listarId(int idReserva);

	List<Reserva> listar();

}
