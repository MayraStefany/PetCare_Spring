package tr.com.jowl.service;

import java.util.List;
import java.util.Optional;

import tr.com.jowl.entity.Cliente;

public interface IClienteService {

	public Integer insertar(Cliente cliente);

	public Integer actualizar(Cliente cliente);

	public void eliminar(int idcliente);

	List<Cliente> buscarNombre(String nombreCliente);

	Optional<Cliente> listarId(int idCliente);

	List<Cliente> listar();

}
