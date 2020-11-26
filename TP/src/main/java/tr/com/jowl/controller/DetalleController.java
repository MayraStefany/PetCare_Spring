package tr.com.jowl.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import tr.com.jowl.entity.DetalleReceta;
import tr.com.jowl.service.IDetalleService;
import tr.com.jowl.service.IMedicamentoService;
import tr.com.jowl.service.IRecetaService;

@Controller
@RequestMapping("/detalles")
public class DetalleController {

	@Autowired
	private IDetalleService dService;
	
	@Autowired
	private IRecetaService rService;
	
	@Autowired
	private IMedicamentoService mService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "home";
	}
	
	@GetMapping("/nuevo")
	public String nuevoDetalle(Model model) {
		model.addAttribute("detalle", new DetalleReceta());
		model.addAttribute("listaRecetas", rService.listar());
		model.addAttribute("listaMedicamentos", mService.listar());
		return "detalle/detalle";
	}

	@PostMapping("/guardar")
	public String guardarDetalle(@Valid DetalleReceta detalle, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaRecetas", rService.listar());
			model.addAttribute("listaMedicamentos", mService.listar());
			return "/detalle/detalle";
		} else {
			int rpta = dService.insertar(detalle);
			if (rpta > 5) {
				model.addAttribute("listaRecetas", rService.listar());
				model.addAttribute("listaMedicamentos", mService.listar());
				model.addAttribute("mensaje", "ya existe registrado un Detalle");
				return "/detalle/detalle";
			} else {
				model.addAttribute("mensaje", "Detalle registrado correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaDetalles", dService.listar());
		return "/detalle/listaDetalle";
	}

	@GetMapping("/listar")
	public String listarDetalles(Model model) {
		try {
			model.addAttribute("detalle", new DetalleReceta());
			model.addAttribute("listaDetalles", dService.listar());
		} catch (Exception e) {
			model.addAttribute("mensaje", e.getMessage());
		}
		return "/detalle/listaDetalle";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				dService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente los datos del Detalle de la receta");
			} else {
				model.put("mensaje", "No se puede eliminar el Detalle");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el Detalle");
		}

		model.put("listaDetalles", dService.listar());
		return "redirect:/detalles/listar";
	}
}
