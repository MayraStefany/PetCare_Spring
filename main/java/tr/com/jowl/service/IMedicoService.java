package tr.com.jowl.service;

import java.util.List;
import java.util.Optional;

import tr.com.jowl.entity.Medico;

public interface IMedicoService {

	public Integer insertar(Medico medico);

	public Integer actualizar(Medico medico);

	public void eliminar(int idMedico);

	List<Medico> buscarNombre(String nombreMedico);

	List<Medico> buscarIdVeterinaria(int idVeterinaria);

	Optional<Medico> listarId(int idMedico);

	List<Medico> listar();

}
