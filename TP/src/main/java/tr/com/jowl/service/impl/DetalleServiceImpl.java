package tr.com.jowl.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.jowl.entity.DetalleReceta;
import tr.com.jowl.entity.Medico;
import tr.com.jowl.entity.Receta;
import tr.com.jowl.repository.DetalleRepository;
import tr.com.jowl.repository.RecetaRepository;
import tr.com.jowl.service.IDetalleService;

@Service
public class DetalleServiceImpl implements IDetalleService {

	@Autowired
	private DetalleRepository aR;

	@Autowired
	private RecetaRepository rR;

	@Override
	public Integer insertar(DetalleReceta detalle) {

		int rpta = 1;
		Optional<Receta> listaReceta = rR.findById(detalle.getRecetaDetalle().getIdReceta());
		Receta obj2 = new Receta();
		obj2 = listaReceta.get();

		if (rpta == 1) {
			DetalleReceta obj = new DetalleReceta();

			aR.save(detalle);

			Optional<DetalleReceta> lista = aR.findById(detalle.getIdDetalle());
			obj = lista.get();

			obj2.setTotalReceta(obj2.getTotalReceta() + obj.getPrecioDetalle());
			obj2.setCantidadReceta(obj2.getCantidadReceta() + 1);

		}
		return rpta;
	}

	@Override
	public void eliminar(int idDetalle) {
		aR.deleteById(idDetalle);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<DetalleReceta> listarId(int idDetalle) {
		return aR.findById(idDetalle);
	}

	@Override
	public List<Medico> buscarIdReceta(int idReceta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetalleReceta> listar() {
		return aR.findAll();
	}

}
