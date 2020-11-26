package tr.com.jowl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "atencion")
public class Atencion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAtencion;

	@ManyToOne
	@JoinColumn(name = "idReserva")
	private Reserva reservaAtencion;

	@Min(0)
	@Max(5)
	@Column(name = "calificacion", nullable = false)
	private int calificacionAtencion;

	@NotEmpty(message = "Ingresar la descripcion de la atencion")
	@Column(name = "descripcion", nullable = false, length = 60)
	private String descripcionAtencion;

	public Atencion(int idAtencion, Reserva reservaAtencion, int calificacionAtencion, String descripcionAtencion) {
		super();
		this.idAtencion = idAtencion;
		this.reservaAtencion = reservaAtencion;
		this.calificacionAtencion = calificacionAtencion;
		this.descripcionAtencion = descripcionAtencion;
	}

	public Atencion() {
		super();
	}

	public int getIdAtencion() {
		return idAtencion;
	}

	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}

	public Reserva getReservaAtencion() {
		return reservaAtencion;
	}

	public void setReservaAtencion(Reserva reservaAtencion) {
		this.reservaAtencion = reservaAtencion;
	}

	public int getCalificacionAtencion() {
		return calificacionAtencion;
	}

	public void setCalificacionAtencion(int calificacionAtencion) {
		this.calificacionAtencion = calificacionAtencion;
	}

	public String getDescripcionAtencion() {
		return descripcionAtencion;
	}

	public void setDescripcionAtencion(String descripcionAtencion) {
		this.descripcionAtencion = descripcionAtencion;
	}

}
