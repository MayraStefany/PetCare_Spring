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

import tr.com.jowl.entity.Reserva;
import tr.com.jowl.service.IClienteService;
import tr.com.jowl.service.IMascotaService;
import tr.com.jowl.service.IVeterinariaService;
import tr.com.jowl.service.IReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	private IReservaService rService;

	@Autowired
	private IClienteService cService;

	@Autowired
	private IVeterinariaService vService;

	@Autowired
	private IMascotaService mService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "home";
	}

	@RequestMapping("/irAjustes")
	public String irAjuste() {
		return "ajustes";
	}

	@GetMapping("/nuevo")
	public String nuevaReserva(Model model) {
		model.addAttribute("reserva", new Reserva());
		model.addAttribute("listaClientes", cService.listar());
		model.addAttribute("listaVeterinarias", vService.listar());
		model.addAttribute("listaMascotas", mService.listar());
		return "reserva/reserva";
	}

	@PostMapping("/guardar")
	public String guardarReserva(@Valid Reserva reserva, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaClientes", cService.listar());
			model.addAttribute("listaVeterinarias", vService.listar());
			model.addAttribute("listaMascotas", mService.listar());
			return "/reserva/reserva";
		} else {
			int rpta = rService.insertar(reserva);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe registrado una reserva con el mismo codigo");
				model.addAttribute("listaClientes", cService.listar());
				model.addAttribute("listaVeterinarias", vService.listar());
				model.addAttribute("listaMascotas", mService.listar());
				return "/reserva/reserva";
			} else {
				model.addAttribute("mensaje", "Reserva registrada correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaReservas", rService.listar());
		model.addAttribute("listaClientes", cService.listar());
		model.addAttribute("listaVeterinarias", vService.listar());
		model.addAttribute("listaMascotas", mService.listar());
		return "/reserva/listaReserva";
	}

	@GetMapping("/listar")
	public String listarReservas(Model model) {
		try {
			model.addAttribute("reserva", new Reserva());
			model.addAttribute("listaReservas", rService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/reserva/listaReserva";
	}

	@GetMapping("/editar/{id}") // modificar
	public String editarReserva(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Reserva> reserva = rService.listarId(id);
			if (!reserva.isPresent()) {
				model.addAttribute("info", "No existe ninguna Reserva registrado con este codigo");
				return "redirect:/reservas/listar";
			} else {
				model.addAttribute("reserva", reserva.get());
				model.addAttribute("listaClientes", cService.listar());
				model.addAttribute("listaVeterinarias", vService.listar());
				model.addAttribute("listaMascotas", mService.listar());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/reserva/reserva";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				rService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente los datos de la Reserva");
			} else {
				model.put("mensaje", "No se puede eliminar los datos de la Reserva");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar los datos de la Reserva");
		}

		model.put("listaReservas", rService.listar());
		return "redirect:/reservas/listar";
	}

	@RequestMapping("/buscar")
	public String buscarEstado(Map<String, Object> model, @ModelAttribute Reserva reserva) throws ParseException {

		List<Reserva> listaReservas;

		reserva.setEstadoReserva(reserva.getEstadoReserva());
		listaReservas = rService.buscarEstadoReserva(reserva.getEstadoReserva());
		if (listaReservas.isEmpty()) {
			model.put("mensaje", "No se encontró una reserva en este estado");
		}
		model.put("listaReservas", listaReservas);
		return "reserva/listaReserva";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Reserva> reserva = rService.listarId(id);
		if (reserva == null) {
			flash.addFlashAttribute("error", "La reserva no existe en la base de datos.");
			return "redirect:/reservas/listar";
		}

		model.put("reserva", reserva.get());

		return "reserva/ver";
	}
}
