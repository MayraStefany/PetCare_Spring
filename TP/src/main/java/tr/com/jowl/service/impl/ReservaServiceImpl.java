package tr.com.jowl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.jowl.entity.Reserva;
import tr.com.jowl.repository.ReservaRepository;
import tr.com.jowl.service.IReservaService;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private ReservaRepository caR;

	@Override
	@Transactional
	public Integer insertar(Reserva reserva) {
		int rpta = 0;
		if (rpta == 0) {
			caR.save(reserva);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void eliminar(int idReserva) {
		caR.deleteById(idReserva);
	}

	@Override
	public List<Reserva> buscarNombreCliente(String cliente) {
		return caR.findByNombreCliente(cliente);
	}

	@Override
	public List<Reserva> buscarEstadoReserva(String estado) {
		return caR.findByEstadoReserva(estado);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Reserva> listarId(int idReserva) {
		return caR.findById(idReserva);
	}

	@Override
	public List<Reserva> listar() {
		return caR.findAll();
	}

}
