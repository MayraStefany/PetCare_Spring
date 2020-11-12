package tr.com.jowl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.jowl.entity.Medico;
import tr.com.jowl.repository.MedicoRepository;
import tr.com.jowl.service.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService {

	@Autowired
	private MedicoRepository dR;

	@Override
	@Transactional
	public Integer insertar(Medico medico) {
		int rpta = dR.contarDni(medico.getDniMedico());
		if (rpta == 0) {
			dR.save(medico);
		}
		return rpta;
	}

	@Override
	@Transactional
	public Integer actualizar(Medico medico) {
		int rpta = dR.contarDni(medico.getDniMedico());
		if (rpta < 2) {
			dR.save(medico);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void eliminar(int idMedico) {
		dR.deleteById(idMedico);
	}

	@Override
	public List<Medico> buscarNombre(String nombreMedico) {
		return dR.findByNombreMedico(nombreMedico);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Medico> listarId(int idMedico) {
		return dR.findById(idMedico);
	}

	@Override
	public List<Medico> listar() {
		return dR.findAll();
	}

	@Override
	public List<Medico> buscarIdVeterinaria(int idVeterinaria) {
		return dR.buscarporidVeterinariaMedico(idVeterinaria);
		
	}

	

}
