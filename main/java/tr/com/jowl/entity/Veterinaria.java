package tr.com.jowl.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "veterinaria")
public class Veterinaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVeterinaria;

	@NotEmpty(message = "Ingresa el nombre de la Veterinaria")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombreVeterinaria;

	@Size(min = 11, max = 11, message = "EL RUC tiene que ser de 11 d\u00edgitos")
	@NotEmpty(message = "Ingresa el RUC de la Veterinaria")
	@Column(name = "ruc", nullable = false, length = 11)
	private String rucVeterinaria;

	@Min(0)
	@Max(15)
	@Column(name = "Tiempo", nullable = false)
	private int tiempoVeterinaria;

	@NotEmpty(message = "Ingresa el horario de atencion de la veterinaria")
	@Column(name = "horario", nullable = false, length = 50)
	private String horarioVeterinaria;

	@NotEmpty(message = "Ingresar el estado actual de la veterinaria")
	@Column(name = "estado", nullable = false, length = 50)
	private String estadoVeterinaria;

	@NotEmpty(message = "Ingresar la direccion en la que se encuentra la veterinaria")
	@Column(name = "direccion", nullable = false, length = 70)
	private String direccionVeterinaria;

	@NotEmpty(message = "Ingresar el distrito en el que se encuentra la veterinaria")
	@Column(name = "distrito", nullable = false, length = 50)
	private String distritoVeterinaria;

	@Pattern(regexp = "[\\d]{9}", message = "El n\u00famero de contacto tiene que ser de 9 d\u00edgitos y no puede ingresar letras")
	@NotEmpty(message = "Ingresa el numero de contacto de la veterinaria")
	@Column(name = "telefono", nullable = false, length = 9)
	private String telefonoVeterinaria;

	public Veterinaria(int idVeterinaria, String nombreVeterinaria, String rucVeterinaria, int tiempoVeterinaria,
			String horarioVeterinaria, String estadoVeterinaria, String direccionVeterinaria,
			String distritoVeterinaria, String telefonoVeterinaria) {
		super();
		this.idVeterinaria = idVeterinaria;
		this.nombreVeterinaria = nombreVeterinaria;
		this.rucVeterinaria = rucVeterinaria;
		this.tiempoVeterinaria = tiempoVeterinaria;
		this.horarioVeterinaria = horarioVeterinaria;
		this.estadoVeterinaria = estadoVeterinaria;
		this.direccionVeterinaria = direccionVeterinaria;
		this.distritoVeterinaria = distritoVeterinaria;
		this.telefonoVeterinaria = telefonoVeterinaria;
	}

	public Veterinaria() {
		super();
	}

	public int getIdVeterinaria() {
		return idVeterinaria;
	}

	public void setIdVeterinaria(int idVeterinaria) {
		this.idVeterinaria = idVeterinaria;
	}

	public String getNombreVeterinaria() {
		return nombreVeterinaria;
	}

	public void setNombreVeterinaria(String nombreVeterinaria) {
		this.nombreVeterinaria = nombreVeterinaria;
	}

	public String getRucVeterinaria() {
		return rucVeterinaria;
	}

	public void setRucVeterinaria(String rucVeterinaria) {
		this.rucVeterinaria = rucVeterinaria;
	}

	public int getTiempoVeterinaria() {
		return tiempoVeterinaria;
	}

	public void setTiempoVeterinaria(int tiempoVeterinaria) {
		this.tiempoVeterinaria = tiempoVeterinaria;
	}

	public String getHorarioVeterinaria() {
		return horarioVeterinaria;
	}

	public void setHorarioVeterinaria(String horarioVeterinaria) {
		this.horarioVeterinaria = horarioVeterinaria;
	}

	public String getEstadoVeterinaria() {
		return estadoVeterinaria;
	}

	public void setEstadoVeterinaria(String estadoVeterinaria) {
		this.estadoVeterinaria = estadoVeterinaria;
	}

	public String getDireccionVeterinaria() {
		return direccionVeterinaria;
	}

	public void setDireccionVeterinaria(String direccionVeterinaria) {
		this.direccionVeterinaria = direccionVeterinaria;
	}

	public String getDistritoVeterinaria() {
		return distritoVeterinaria;
	}

	public void setDistritoVeterinaria(String distritoVeterinaria) {
		this.distritoVeterinaria = distritoVeterinaria;
	}

	public String getTelefonoVeterinaria() {
		return telefonoVeterinaria;
	}

	public void setTelefonoVeterinaria(String telefonoVeterinaria) {
		this.telefonoVeterinaria = telefonoVeterinaria;
	}

}
