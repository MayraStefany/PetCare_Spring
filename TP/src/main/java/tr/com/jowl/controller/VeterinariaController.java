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

import tr.com.jowl.entity.Veterinaria;
import tr.com.jowl.entity.Medico;
import tr.com.jowl.service.IVeterinariaService;
import tr.com.jowl.service.IMedicoService;

@Controller
@RequestMapping("/veterinarias") // tt
public class VeterinariaController {
	@Autowired
	private IVeterinariaService cService;

	@Autowired
	private IMedicoService mService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "home";
	}

	@GetMapping("/nuevo")
	public String nuevaVeterinaria(Model model) {
		model.addAttribute("veterinaria", new Veterinaria());
		return "veterinaria/veterinaria";
	}

	@PostMapping("/guardar")
	public String guardarVeterinaria(@Valid Veterinaria veterinaria, BindingResult result, Model model,
			SessionStatus status) throws Exception {

		int rpta = cService.insertar(veterinaria);
		if (rpta > 0) {
			model.addAttribute("mensaje", "No se puede guardar esta Veterinaria");
			return "/veterinaria/Veterinaria";
		} else {
			model.addAttribute("mensaje", "Veterinaria registrada correctamente");
			status.setComplete();
		}

		model.addAttribute("listaVeterinarias", cService.listar());
		return "/veterinaria/listaVeterinaria";
	}

	@PostMapping("/actualizar")
	public String actualizarVeterinaria(@Valid Veterinaria veterinaria, BindingResult result, Model model,
			SessionStatus status) throws Exception {

		int rpta = cService.insertar(veterinaria);
		if (rpta > 0) {
			model.addAttribute("mensaje", "No se puede guardar esta Veterinaria");
			return "/veterinaria/Veterinaria2";
		} else {
			model.addAttribute("mensaje", "Veterinaria actualizada correctamente");
			status.setComplete();
		}

		model.addAttribute("listaVeterinarias", cService.listar());
		return "/veterinaria/listaVeterinaria";
	}

	@GetMapping("/listar")
	public String listarVeterinarias(Model model) {
		try {
			model.addAttribute("veterinaria", new Veterinaria());
			model.addAttribute("listaVeterinarias", cService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/veterinaria/listaVeterinaria";
	}

	@GetMapping("/editar/{id}") // modificar
	public String editarVeterinaria(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Veterinaria> veterinaria = cService.listarId(id);
			if (!veterinaria.isPresent()) {
				model.addAttribute("info", "No existe ninguna Veterinaria registrado con este codigo");
				return "redirect:/veterinarias/listar";
			} else {
				model.addAttribute("veterinaria", veterinaria.get());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/Veterinaria/Veterinaria2";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				cService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente los datos de la Veterinaria seleccionado");
			} else {
				model.put("mensaje", "No se puede eliminar los datos de la Veterinaria");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar los datos de la Veterinaria");
		}

		model.put("listaVeterinarias", cService.listar());
		return "redirect:/veterinarias/listar";
	}

	@RequestMapping("/buscarNombre")
	public String buscarNombre(Map<String, Object> model, @ModelAttribute Veterinaria veterinaria)
			throws ParseException {

		List<Veterinaria> listaVeterinarias;

		veterinaria.setNombreVeterinaria(veterinaria.getNombreVeterinaria());
		listaVeterinarias = cService.buscarNombre(veterinaria.getNombreVeterinaria());
		if (listaVeterinarias.isEmpty()) {
			model.put("mensaje", "No se encontro ninguna Veterinaria con ese nombre");
		} else
			model.put("listaVeterinarias", listaVeterinarias);
		return "veterinaria/listaVeterinaria";
	}

	@RequestMapping("/buscarDistrito")
	public String buscarDistrito(Map<String, Object> model, @ModelAttribute Veterinaria veterinaria)
			throws ParseException {

		List<Veterinaria> listaVeterinarias;

		veterinaria.setDistritoVeterinaria(veterinaria.getDistritoVeterinaria());
		listaVeterinarias = cService.buscarDistrito(veterinaria.getDistritoVeterinaria());
		if (listaVeterinarias.isEmpty()) {
			model.put("mensaje", "No se encontró ninguna Veterinaria en ese distrito");
		} else
			model.put("listaVeterinarias", listaVeterinarias);
		return "veterinaria/listaVeterinaria";
	}

	@RequestMapping("/buscarHorario")
	public String buscarHorario(Map<String, Object> model, @ModelAttribute Veterinaria veterinaria)
			throws ParseException {

		List<Veterinaria> listaVeterinarias;

		veterinaria.setHorarioVeterinaria(veterinaria.getHorarioVeterinaria());
		listaVeterinarias = cService.buscarHorario(veterinaria.getHorarioVeterinaria());
		if (listaVeterinarias.isEmpty()) {
			model.put("mensaje", "No se encontró ninguna Veterinaria en ese distrito");
		} else
			model.put("listaVeterinarias", listaVeterinarias);
		return "veterinaria/listaVeterinaria";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Veterinaria> veterinaria = cService.listarId(id);
		if (veterinaria == null) {
			flash.addFlashAttribute("error", "El empleado no existe en la base de datos.");
			return "redirect:/veterinarias/listar";
		}

		int aux;
		aux = id;
		List<Medico> listaMedicosdelaVeterinaria;
		listaMedicosdelaVeterinaria = mService.buscarIdVeterinaria(aux);
		model.put("listaMedicosVeterinarias", listaMedicosdelaVeterinaria);

		model.put("veterinaria", veterinaria.get());

		return "veterinaria/ver";
	}
}
