package tr.com.jowl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReserva;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente clienteReserva;

	@ManyToOne
	@JoinColumn(name = "idVeterinaria")
	private Veterinaria veterinariaReserva;

	@ManyToOne
	@JoinColumn(name = "idMascota")
	private Mascota mascotaReserva;

	@NotEmpty(message = "Ingresar el estado en el que se encuentra actualmente la reserva")
	@Column(name = "estado", nullable = false, length = 60)
	private String estadoReserva;

	@NotEmpty(message = "Ingresar la descripcion de la reserva")
	@Column(name = "nombre", nullable = false, length = 60)
	private String descripcionReserva;

	@NotNull(message = "La fecha es obligatoria")
	@Future(message = "La fecha debe estar en el Futuro")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaReserva;

	public Reserva(int idReserva, Cliente clienteReserva, Veterinaria veterinariaReserva, Mascota mascotaReserva,
			String estadoReserva, String descripcionReserva, Date fechaReserva) {
		super();
		this.idReserva = idReserva;
		this.clienteReserva = clienteReserva;
		this.veterinariaReserva = veterinariaReserva;
		this.mascotaReserva = mascotaReserva;
		this.estadoReserva = estadoReserva;
		this.descripcionReserva = descripcionReserva;
		this.fechaReserva = fechaReserva;
	}

	public Reserva() {
		super();
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public Cliente getClienteReserva() {
		return clienteReserva;
	}

	public void setClienteReserva(Cliente clienteReserva) {
		this.clienteReserva = clienteReserva;
	}

	public Veterinaria getVeterinariaReserva() {
		return veterinariaReserva;
	}

	public void setVeterinariaReserva(Veterinaria veterinariaReserva) {
		this.veterinariaReserva = veterinariaReserva;
	}

	public Mascota getMascotaReserva() {
		return mascotaReserva;
	}

	public void setMascotaReserva(Mascota mascotaReserva) {
		this.mascotaReserva = mascotaReserva;
	}

	public String getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public String getDescripcionReserva() {
		return descripcionReserva;
	}

	public void setDescripcionReserva(String descripcionReserva) {
		this.descripcionReserva = descripcionReserva;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

}
