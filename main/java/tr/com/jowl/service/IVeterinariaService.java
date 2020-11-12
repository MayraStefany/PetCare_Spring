package tr.com.jowl.service;

import java.util.List;
import java.util.Optional;

import tr.com.jowl.entity.Veterinaria;

public interface IVeterinariaService {

	public Integer insertar(Veterinaria veterinaria);

	public void eliminar(int idVeterinaria);

	List<Veterinaria> buscarNombre(String nombreVeterinaria);

	List<Veterinaria> buscarHorario(String horarioVeterinaria);

	List<Veterinaria> buscarDistrito(String distritoVeterinaria);

	Optional<Veterinaria> listarId(int idVeterinaria);

	List<Veterinaria> listar();
}
