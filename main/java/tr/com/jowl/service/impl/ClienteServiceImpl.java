package tr.com.jowl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.jowl.entity.Cliente;
import tr.com.jowl.repository.ClienteRepository;
import tr.com.jowl.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository dR;

	@Override
	@Transactional
	public Integer insertar(Cliente cliente) {
		int rpta = dR.contarDni(cliente.getDniCliente());
		if (rpta == 0) {
			dR.save(cliente);
		}
		return rpta;
	}

	@Override
	@Transactional
	public Integer actualizar(Cliente cliente) {
		int rpta = dR.contarDni(cliente.getDniCliente());
		if (rpta < 2) {
			dR.save(cliente);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void eliminar(int idCliente) {
		dR.deleteById(idCliente);
	}

	@Override
	public List<Cliente> buscarNombre(String nombreCliente) {
		return dR.findByNombreCliente(nombreCliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> listarId(int idCliente) {
		return dR.findById(idCliente);
	}

	@Override
	public List<Cliente> listar() {
		return dR.findAll();
	}

}
