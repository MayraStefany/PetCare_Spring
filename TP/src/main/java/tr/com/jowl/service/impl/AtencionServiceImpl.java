package tr.com.jowl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.jowl.entity.Atencion;
import tr.com.jowl.repository.AtencionRepository;
import tr.com.jowl.service.IAtencionService;

@Service
public class AtencionServiceImpl implements IAtencionService {

	@Autowired
	private AtencionRepository aR;

	@Override
	@Transactional
	public Integer insertar(Atencion atencion) {
		int rpta = 0;
		if (rpta == 0) {
			aR.save(atencion);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void eliminar(int idAtencion) {
		aR.deleteById(idAtencion);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Atencion> listarId(int idAtencion) {
		return aR.findById(idAtencion);
	}

	@Override
	public List<Atencion> listar() {
		return aR.findAll();
	}
}
