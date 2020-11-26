package tr.com.jowl.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tr.com.jowl.entity.Atencion;
import tr.com.jowl.service.IAtencionService;
import tr.com.jowl.service.IReservaService;

@Controller
@RequestMapping("/atenciones")
public class AtencionController {

	@Autowired
	private IAtencionService dService;

	@Autowired
	private IReservaService cService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "home";
	}

	@GetMapping("/nuevo")
	public String nuevoAtencion(Model model) {
		model.addAttribute("atencion", new Atencion());
		model.addAttribute("listaReservas", cService.listar());
		return "atencion/atencion";
	}

	@PostMapping("/guardar")
	public String guardarAtencion(@Valid Atencion atencion, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaReservas", cService.listar());
			return "/atencion/atencion";
		} else {
			int rpta = dService.insertar(atencion);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe registrado un Atencion con el mismo DNI");
				model.addAttribute("listaReservas", cService.listar());
				return "/atencion/atencion";
			} else {
				model.addAttribute("mensaje", "Atencion registrado correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaAtenciones", dService.listar());
		return "/atencion/listaAtencion";
	}

	@PostMapping("/actualizar")
	public String actualizarAtencion(@Valid Atencion atencion, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaReservas", cService.listar());
			return "/atencion/atencion2";
		} else {
			int rpta = dService.insertar(atencion);
			if (rpta > 1) {
				model.addAttribute("mensaje", "ya existe registrado un Atencion con el mismo DNI");
				model.addAttribute("listaReservas", cService.listar());
				return "/atencion/atencion2";
			} else {
				model.addAttribute("mensaje", "Se actualizaron los datos de la Atencion exitosamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaAtenciones", dService.listar());
		return "/atencion/listaAtencion";
	}

	@GetMapping("/listar")
	public String listarAtencions(Model model) {
		try {
			model.addAttribute("atencion", new Atencion());
			model.addAttribute("listaAtenciones", dService.listar());
		} catch (Exception e) {
			model.addAttribute("mensaje", e.getMessage());
		}
		return "/atencion/listaAtencion";
	}

	@GetMapping("/editar/{id}") // modificar
	public String editarAtencion(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Atencion> atencion = dService.listarId(id);
			if (!atencion.isPresent()) {
				model.addAttribute("mensaje", "No existe ningun Atencion registrado");
				return "redirect:/atenciones/listar";
			} else {
				model.addAttribute("atencion", atencion.get());
				model.addAttribute("listaReservas", cService.listar());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/atencion/atencion2";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				dService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente los datos de la Atencion");
			} else {
				model.put("mensaje", "No se puede eliminar la Atencion");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar la Atencion");
		}

		model.put("listaAtenciones", dService.listar());
		return "redirect:/atenciones/listar";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Atencion> atencion = dService.listarId(id);
		if (atencion == null) {
			flash.addFlashAttribute("error", "El empleado no existe en la base de datos.");
			return "redirect:/empleados/listar";
		}

		model.put("atencion", atencion.get());

		return "atencion/ver";
	}
}
