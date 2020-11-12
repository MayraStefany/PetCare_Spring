package tr.com.jowl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.jowl.entity.Mascota;
import tr.com.jowl.repository.MascotaRepository;
import tr.com.jowl.service.IMascotaService;

@Service
public class MascotaServiceImpl implements IMascotaService {

	@Autowired
	private MascotaRepository aR;

	@Override
	@Transactional
	public Integer insertar(Mascota mascota) {
		int rpta = 0;
		if (rpta == 0) {
			aR.save(mascota);
		}
		return rpta;
	}

	@Override
	@Transactional
	public Integer actualizar(Mascota mascota) {
		int rpta = 0;
		if (rpta == 0) {
			aR.save(mascota);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void eliminar(int idMascota) {
		aR.deleteById(idMascota);
	}

	@Override
	public List<Mascota> buscarNombre(String nombreMascota) {
		return aR.findByNombreMascota(nombreMascota);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Mascota> listarId(int idMascota) {
		return aR.findById(idMascota);
	}

	@Override
	public List<Mascota> listar() {
		return aR.findAll();
	}

	@Override
	public List<Mascota> buscarRaza(String razaMascota) {
		return aR.findByRazaMascota(razaMascota);
	}

}
