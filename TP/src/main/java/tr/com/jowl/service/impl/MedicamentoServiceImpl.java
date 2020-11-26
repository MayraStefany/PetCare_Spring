package tr.com.jowl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.jowl.entity.Medicamento;
import tr.com.jowl.repository.MedicamentoRepository;
import tr.com.jowl.service.IMedicamentoService;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService {

	@Autowired
	private MedicamentoRepository aR;

	@Override
	@Transactional
	public Integer insertar(Medicamento medicamento) {
		int rpta = 0;
		if (rpta == 0) {
			aR.save(medicamento);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void eliminar(int idMedicamento) {
		aR.deleteById(idMedicamento);
	}

	@Override
	public List<Medicamento> buscarNombre(String nombreMedicamento) {
		return aR.findByNombreMedicamento(nombreMedicamento);
	}

	@Override
	public List<Medicamento> buscarTipo(String tipoMedicamento) {
		return aR.findByTipoMedicamento(tipoMedicamento);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Medicamento> listarId(int idMedicamento) {
		return aR.findById(idMedicamento);
	}

	@Override
	public List<Medicamento> listar() {
		return aR.findAll();
	}

}
