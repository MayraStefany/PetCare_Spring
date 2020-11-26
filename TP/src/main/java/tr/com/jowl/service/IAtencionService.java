package tr.com.jowl.service;

import java.util.List;
import java.util.Optional;

import tr.com.jowl.entity.Atencion;

public interface IAtencionService {

	public Integer insertar(Atencion atencion);

	public void eliminar(int idAtencion);

	Optional<Atencion> listarId(int idAtencion);

	List<Atencion> listar();

}
