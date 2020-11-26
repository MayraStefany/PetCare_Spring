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

import tr.com.jowl.entity.Receta;
import tr.com.jowl.service.IAtencionService;
import tr.com.jowl.service.RecetaService;

@Controller
@RequestMapping("/recetas")
public class RecetaController {

	@Autowired
	private RecetaService dService;

	@Autowired
	private IAtencionService cService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "home";
	}

	@GetMapping("/nuevo")
	public String nuevoReceta(Model model) {
		model.addAttribute("receta", new Receta());
		model.addAttribute("listaAtenciones", cService.listar());
		return "receta/receta";
	}

	@PostMapping("/guardar")
	public String guardarReceta(@Valid Receta receta, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaAtenciones", cService.listar());
			return "/receta/receta";
		} else {
			int rpta = dService.insertar(receta);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe registrado una Receta");
				model.addAttribute("listaReservas", cService.listar());
				return "/receta/receta";
			} else {
				model.addAttribute("mensaje", "Receta registrada correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaRecetas", dService.listar());
		return "/receta/listaReceta";
	}

	@PostMapping("/actualizar")
	public String actualizarReceta(@Valid Receta receta, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaAtenciones", cService.listar());
			return "/receta/receta2";
		} else {
			int rpta = dService.insertar(receta);
			if (rpta > 1) {
				model.addAttribute("mensaje", "ya existe registrado una receta");
				model.addAttribute("listaAtenciones", cService.listar());
				return "/receta/receta2";
			} else {
				model.addAttribute("mensaje", "Se actualizaron los datos de la receta exitosamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaRecetas", dService.listar());
		return "/receta/listaReceta";
	}

	@GetMapping("/listar")
	public String listarRecetas(Model model) {
		try {
			model.addAttribute("receta", new Receta());
			model.addAttribute("listaRecetas", dService.listar());
		} catch (Exception e) {
			model.addAttribute("mensaje", e.getMessage());
		}
		return "/receta/listaReceta";
	}

	@GetMapping("/editar/{id}") // modificar
	public String editarReceta(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Receta> receta = dService.listarId(id);
			if (!receta.isPresent()) {
				model.addAttribute("mensaje", "No existe ningun Receta registrado");
				return "redirect:/recetas/listar";
			} else {
				model.addAttribute("receta", receta.get());
				model.addAttribute("listaAtenciones", cService.listar());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/receta/receta2";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				dService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente los datos de la Receta");
			} else {
				model.put("mensaje", "No se puede eliminar la Receta");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar la Receta");
		}

		model.put("listaRecetas", dService.listar());
		return "redirect:/recetaes/listar";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Receta> receta = dService.listarId(id);
		if (receta == null) {
			flash.addFlashAttribute("error", "El empleado no existe en la base de datos.");
			return "redirect:/empleados/listar";
		}

		model.put("receta", receta.get());

		return "receta/ver";
	}
}
