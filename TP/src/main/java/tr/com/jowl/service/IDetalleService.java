package tr.com.jowl.service;

import java.util.List;
import java.util.Optional;

import tr.com.jowl.entity.DetalleReceta;
import tr.com.jowl.entity.Medico;

public interface IDetalleService {
	
	public Integer insertar(DetalleReceta detalle);

	public void eliminar(int idDetalle);

	Optional<DetalleReceta> listarId(int idDetalle);
	
	List<Medico> buscarIdReceta(int idReceta);

	List<DetalleReceta> listar();
}
