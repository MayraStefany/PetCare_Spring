package tr.com.jowl.service;

import java.util.List;
import java.util.Optional;

import tr.com.jowl.entity.Medicamento;

public interface IMedicamentoService {

	public Integer insertar(Medicamento medicamento);

	public void eliminar(int idMedicamento);

	List<Medicamento> buscarNombre(String nombreMedicamento);

	List<Medicamento> buscarTipo(String tipoMedicamento);

	Optional<Medicamento> listarId(int idMedicamento);

	List<Medicamento> listar();
}
