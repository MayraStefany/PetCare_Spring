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

import tr.com.jowl.entity.Cliente;
import tr.com.jowl.service.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService dService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "home";
	}

	@GetMapping("/nuevo")
	public String nuevoCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "cliente/cliente";
	}

	@PostMapping("/guardar")
	public String guardarCliente(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "/cliente/cliente";
		} else {
			int rpta = dService.insertar(cliente);
			if (rpta > 0) {
				model.addAttribute("mensaje", "ya existe registrado un Cliente con el mismo DNI");
				return "/cliente/cliente";
			} else {
				model.addAttribute("mensaje", "Cliente registrado correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaClientes", dService.listar());
		return "/cliente/listaCliente";
	}

	@PostMapping("/actualizar")
	public String actualizarCliente(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {

			return "/cliente/cliente2";
		} else {
			int rpta = dService.actualizar(cliente);
			if (rpta > 1) {
				model.addAttribute("mensaje", "ya existe registrado un Cliente con el mismo DNI");

				return "/cliente/cliente2";
			} else {
				model.addAttribute("mensaje", "Se actualizaron los datos del Cliente exitosamente");
				status.setComplete();
			}
		}
		model.addAttribute("listaClientes", dService.listar());
		return "/cliente/listaCliente";
	}

	@GetMapping("/listar")
	public String listarClientes(Model model) {
		try {
			model.addAttribute("cliente", new Cliente());
			model.addAttribute("listaClientes", dService.listar());
		} catch (Exception e) {
			model.addAttribute("mensaje", e.getMessage());
		}
		return "/cliente/listaCliente";
	}

	@GetMapping("/editar/{id}") // modificar
	public String editarCliente(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Cliente> cliente = dService.listarId(id);
			if (!cliente.isPresent()) {
				model.addAttribute("mensaje", "No existe ningun Cliente registrado");
				return "redirect:/clientes/listar";
			} else {
				model.addAttribute("cliente", cliente.get());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/cliente/cliente2";
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				dService.eliminar(id);
				model.put("mensaje", "Se elimino correctamente los datos del Cliente");
			} else {
				model.put("mensaje", "No se puede eliminar el Cliente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el Cliente");
		}

		model.put("listaClientes", dService.listar());
		return "redirect:/clientes/listar";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Cliente cliente) throws ParseException {

		List<Cliente> listaClientes;

		cliente.setNombreCliente(cliente.getNombreCliente());
		listaClientes = dService.buscarNombre(cliente.getNombreCliente());
		if (listaClientes.isEmpty()) {
			model.put("mensaje", "No se encontró al Cliente");
		}
		model.put("listaClientes", listaClientes);
		return "cliente/listaCliente";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Cliente> cliente = dService.listarId(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El empleado no existe en la base de datos.");
			return "redirect:/empleados/listar";
		}

		model.put("cliente", cliente.get());

		return "cliente/ver";
	}
}
