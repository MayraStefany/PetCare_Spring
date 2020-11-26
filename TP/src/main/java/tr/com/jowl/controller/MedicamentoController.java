package tr.com.jowl.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tr.com.jowl.entity.Medicamento;
import tr.com.jowl.service.IMedicamentoService;

@Controller
@RequestMapping("/medicamentos")
public class MedicamentoController {

	@Autowired
	private IMedicamentoService aService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "home";
	}

	@GetMapping("/nuevo")
	public String nuevoMedicamento(Model model) {
		model.addAttribute("medicamento", new Medicamento());
		return "medicamento/medicamento";
	}

	@PostMapping("/guardar")
	public String guardarMedicamento(@Valid Medicamento medicamento, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/medicamento/medicamento";
		} else {
			int rpta = aService.insertar(medicamento);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Error al registrar");
				return "/medicamento/medicamento";
			} else {
				model.addAttribute("mensaje", "Medicamento guardado correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaMedicamentos", aService.listar());
		return "/medicamento/listaMedicamento";
	}

	@PostMapping("/actualizar")
	public String actualizarMedicamento(@Valid Medicamento medicamento, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "/medicamento/medicamento";
		} else {
			int rpta = aService.insertar(medicamento);
			if (rpta > 1) {
				model.addAttribute("mensaje", "Error al actualizar");
				return "/medicamento/medicamento";
			} else {
				model.addAttribute("mensaje", "Se actualizaron los datos del medicamento de forma exitosa");
				status.setComplete();
			}
		}
		model.addAttribute("listaMedicamentos", aService.listar());
		return "/medicamento/listaMedicamento";
	}

	@GetMapping("/listar")
	public String listarMedicamento(Model model) {
		try {
			model.addAttribute("medicamento", new Medicamento());
			model.addAttribute("listaMedicamentos", aService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/medicamento/listaMedicamento";
	}

	@GetMapping("/editar/{id}") // modificar
	public String editarMedicamento(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Medicamento> medicamento = aService.listarId(id);
			if (!medicamento.isPresent()) {
				model.addAttribute("mensaje", "No existe ningun medicamento con el codigo solicitado");
				model.addAttribute("listaMedicamentos", aService.listar());
				return "/medicamento/listaMedicamento";
			} else {
				model.addAttribute("medicamento", medicamento.get());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/medicamento/medicamento2";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				aService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente los datos del medicamento");
			} else {
				model.put("mensaje", "No se puede eliminar los datos del medicamento");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar los datos del medicamento");
		}

		model.put("listaMedicamentos", aService.listar());

		return "redirect:/medicamentos/listar";
	}

	@RequestMapping("/busquedapornombre")
	public String buscarporNombre(Map<String, Object> model, @ModelAttribute Medicamento medicamento)
			throws ParseException {

		List<Medicamento> ListaMedicamentos;

		medicamento.setNombreMedicamento(medicamento.getNombreMedicamento());
		ListaMedicamentos = aService.buscarNombre(medicamento.getNombreMedicamento());
		if (ListaMedicamentos.isEmpty()) {
			model.put("mensaje", "No se encontró un medicamento con ese nombre");
		}
		model.put("listaMedicamentos", ListaMedicamentos);
		return "medicamento/listaMedicamento";
	}

	@RequestMapping("/busquedaportipo")
	public String buscarporRaza(Map<String, Object> model, @ModelAttribute Medicamento medicamento)
			throws ParseException {

		List<Medicamento> listaMedicamentos;

		medicamento.setTipoMedicamento(medicamento.getTipoMedicamento());
		listaMedicamentos = aService.buscarTipo(medicamento.getTipoMedicamento());
		if (listaMedicamentos.isEmpty()) {
			model.put("mensaje", "No se encontró ningun Medicamento registrado de ese tipo");
		}
		model.put("listaMedicamentos", listaMedicamentos);
		return "medicamento/listaMedicamento";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Medicamento> medicamento = aService.listarId(id);
		if (medicamento == null) {
			flash.addFlashAttribute("error", "El empleado no existe en la base de datos.");
			return "redirect:/empleados/listar";
		}

		model.put("medicamento", medicamento.get());

		return "medicamento/ver";
	}

}
