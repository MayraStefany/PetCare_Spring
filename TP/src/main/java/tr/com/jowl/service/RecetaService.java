package tr.com.jowl.service;

import java.util.List;
import java.util.Optional;

import tr.com.jowl.entity.Receta;

public interface RecetaService {

	public Integer insertar(Receta receta);

	public void eliminar(int idReceta);

	Optional<Receta> listarId(int idReceta);

	List<Receta> listar();
}
