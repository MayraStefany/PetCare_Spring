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

import tr.com.jowl.entity.Medico;
import tr.com.jowl.service.IVeterinariaService;
import tr.com.jowl.service.IMedicoService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private IMedicoService dService;

	@Autowired
	private IVeterinariaService cService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "home";
	}

	@GetMapping("/nuevo")
	public String nuevoMedico(Model model) {
		model.addAttribute("medico", new Medico());
		model.addAttribute("listaVeterinarias", cService.listar());
		return "medico/medico";
	}

	@PostMapping("/guardar")
	public String guardarMedico(@Valid Medico medico, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaVeterinarias", cService.listar());
			return "/medico/medico";
		} else {
			int rpta = dService.insertar(medico);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe registrado un Medico con el mismo DNI");
				model.addAttribute("listaVeterinarias", cService.listar());
				return "/medico/medico";
			} else {
				model.addAttribute("mensaje", "Medico registrado correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaMedicos", dService.listar());
		return "/medico/listaMedico";
	}

	@PostMapping("/actualizar")
	public String actualizarMedico(@Valid Medico medico, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaVeterinarias", cService.listar());
			return "/medico/medico2";
		} else {
			int rpta = dService.actualizar(medico);
			if (rpta > 1) {
				model.addAttribute("mensaje", "ya existe registrado un Medico con el mismo DNI");
				model.addAttribute("listaVeterinarias", cService.listar());
				return "/medico/medico2";
			} else {
				model.addAttribute("mensaje", "Se actualizaron los datos del Medico exitosamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaMedicos", dService.listar());
		return "/medico/listaMedico";
	}

	@GetMapping("/listar")
	public String listarMedicos(Model model) {
		try {
			model.addAttribute("medico", new Medico());
			model.addAttribute("listaMedicos", dService.listar());
		} catch (Exception e) {
			model.addAttribute("mensaje", e.getMessage());
		}
		return "/medico/listaMedico";
	}

	@GetMapping("/editar/{id}") // modificar
	public String editarMedico(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Medico> medico = dService.listarId(id);
			if (!medico.isPresent()) {
				model.addAttribute("mensaje", "No existe ningun Medico registrado");
				return "redirect:/medicos/listar";
			} else {
				model.addAttribute("medico", medico.get());
				model.addAttribute("listaVeterinarias", cService.listar());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/medico/medico2";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				dService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente los datos del Medico");
			} else {
				model.put("mensaje", "No se puede eliminar el Medico");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el Medico");
		}

		model.put("listaMedicos", dService.listar());
		return "redirect:/medicos/listar";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Medico medico) throws ParseException {

		List<Medico> listaMedicos;

		medico.setNombreMedico(medico.getNombreMedico());
		listaMedicos = dService.buscarNombre(medico.getNombreMedico());
		if (listaMedicos.isEmpty()) {
			model.put("mensaje", "No se encontró al Medico");
		}
		model.put("listaMedicos", listaMedicos);
		return "medico/listaMedico";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Medico> medico = dService.listarId(id);
		if (medico == null) {
			flash.addFlashAttribute("error", "El empleado no existe en la base de datos.");
			return "redirect:/empleados/listar";
		}

		model.put("medico", medico.get());

		return "medico/ver";
	}
}
