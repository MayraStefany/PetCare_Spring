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

import tr.com.jowl.entity.Mascota;
import tr.com.jowl.service.IMascotaService;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {
	@Autowired
	private IMascotaService aService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "home";
	}

	@GetMapping("/nuevo")
	public String nuevaMascota(Model model) {
		model.addAttribute("mascota", new Mascota());
		return "mascota/mascota";
	}

	@PostMapping("/guardar")
	public String guardarMascota(@Valid Mascota mascota, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "/mascota/mascota";
		} else {
			int rpta = aService.insertar(mascota);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Error al registrar");
				return "/mascota/mascota";
			} else {
				model.addAttribute("mensaje", "mascota resgistrada correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaMascotas", aService.listar());
		return "/mascota/listaMascota";
	}

	@PostMapping("/actualizar")
	public String actualizarMascota(@Valid Mascota mascota, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "/mascota/mascota";
		} else {
			int rpta = aService.actualizar(mascota);
			if (rpta > 1) {
				model.addAttribute("mensaje", "Error al actualizar");
				return "/mascota/mascota";
			} else {
				model.addAttribute("mensaje", "Se actualizaron los datos de la Mascota exitosamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaMascotas", aService.listar());
		return "/mascota/listaMascota";
	}

	@GetMapping("/listar")
	public String listarMascota(Model model) {
		try {
			model.addAttribute("mascota", new Mascota());
			model.addAttribute("listaMascotas", aService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/mascota/listaMascota";
	}

	@GetMapping("/editar/{id}") // modificar
	public String editarMascota(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Mascota> mascota = aService.listarId(id);
			if (!mascota.isPresent()) {
				model.addAttribute("mensaje", "No existe ninguna Mascota registrado con este codigo");
				model.addAttribute("listaMascotas", aService.listar());
				return "/mascota/listaMascota";
			} else {
				model.addAttribute("mascota", mascota.get());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/mascota/mascota2";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				aService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente los datos de la Mascota");
			} else {
				model.put("mensaje", "No se puede eliminar los datos de la Mascota");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar los datos de la Mascota");
		}

		model.put("listaMascotas", aService.listar());

		return "redirect:/mascotas/listar";
	}

	@RequestMapping("/busquedapornombre")
	public String buscarporNombre(Map<String, Object> model, @ModelAttribute Mascota mascota) throws ParseException {

		List<Mascota> ListaMascotas;

		mascota.setNombreMascota(mascota.getNombreMascota());
		ListaMascotas = aService.buscarNombre(mascota.getNombreMascota());
		if (ListaMascotas.isEmpty()) {
			model.put("mensaje", "No se encontró a la Mascota");
		}
		model.put("listaMascotas", ListaMascotas);
		return "mascota/listaMascota";
	}
	
	
	@RequestMapping("/busquedaporraza")
	public String buscarporRaza(Map<String, Object> model, @ModelAttribute Mascota mascota) throws ParseException {

		List<Mascota> listaMascotas;

		mascota.setRazaMascota(mascota.getRazaMascota());
		listaMascotas = aService.buscarRaza(mascota.getRazaMascota());
		if (listaMascotas.isEmpty()) {
			model.put("mensaje", "No se encontró a la Mascota");
		}
		model.put("listaMascotas", listaMascotas);
		return "mascota/listaMascota";
	}
	
	
	

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Mascota> mascota = aService.listarId(id);
		if (mascota == null) {
			flash.addFlashAttribute("error", "El empleado no existe en la base de datos.");
			return "redirect:/empleados/listar";
		}

		model.put("mascota", mascota.get());

		return "mascota/ver";
	}

}
