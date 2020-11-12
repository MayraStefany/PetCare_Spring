package tr.com.jowl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.jowl.entity.Veterinaria;
import tr.com.jowl.repository.VeterinariaRepository;
import tr.com.jowl.service.IVeterinariaService;

@Service
public class VeterinariaServiceImpl implements IVeterinariaService {

	@Autowired
	private VeterinariaRepository cR;

	@Override
	@Transactional
	public Integer insertar(Veterinaria veterinaria) {
		int rpta = 0;
		if (rpta == 0) {
			cR.save(veterinaria);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void eliminar(int idVeterinaria) {
		cR.deleteById(idVeterinaria);
	}

	@Override
	public List<Veterinaria> buscarNombre(String nombreVeterinaria) {
		return cR.findByNombreVeterinaria(nombreVeterinaria);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Veterinaria> listarId(int idVeterinaria) {
		return cR.findById(idVeterinaria);
	}

	@Override
	public List<Veterinaria> listar() {
		return cR.findAll();
	}

	@Override
	public List<Veterinaria> buscarHorario(String horarioVeterinaria) {
		return cR.findByHorarioVeterinaria(horarioVeterinaria);
	}

	@Override
	public List<Veterinaria> buscarDistrito(String distritoVeterinaria) {
		return cR.findByDistritoVeterinaria(distritoVeterinaria);
	}

}
