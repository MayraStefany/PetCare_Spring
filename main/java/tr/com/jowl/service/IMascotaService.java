package tr.com.jowl.service;

import java.util.List;
import java.util.Optional;

import tr.com.jowl.entity.Mascota;

public interface IMascotaService {

	public Integer insertar(Mascota mascota);

	public Integer actualizar(Mascota mascota);

	public void eliminar(int idMascota);

	List<Mascota> buscarNombre(String nombreMascota);
	
	List<Mascota> buscarRaza(String razaMascota);

	Optional<Mascota> listarId(int idMascota);

	List<Mascota> listar();

}
