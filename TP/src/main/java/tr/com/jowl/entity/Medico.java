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
import javax.validation.constraints.Size;

@Entity
@Table(name = "medico")
public class Medico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMedico;

	@NotEmpty(message = "Ingresa el nombre del Medico")
	@Column(name = "nombre", nullable = false, length = 40)
	private String nombreMedico;

	@Size(min = 8, max = 8, message = "EL DNI tiene que ser de 8 d\u00edgitos")
	@NotEmpty(message = "Ingresa el DNI del Medico")
	@Column(name = "dni", nullable = false, length = 8)
	private String dniMedico;

	@Min(0)
	@Max(99)
	@Column(name = "edad", nullable = false)
	private int edadMedico;

	@NotEmpty(message = "Ingresa la direccion del Medico")
	@Column(name = "direccion", nullable = false, length = 70)
	private String direccionMedico;

	@NotEmpty(message = "Ingresa la institucion academica del Medico")
	@Column(name = "institucion", nullable = false, length = 70)
	private String institucionMedico;

	@NotEmpty(message = "Ingresar el estado de la licencia del Medico")
	@Column(name = "estado", nullable = false, length = 40)
	private String estadoMedico;

	@ManyToOne
	@JoinColumn(name = "idVeterinaria")
	private Veterinaria veterinariaMedico;

	public Medico(int idMedico, String nombreMedico, String dniMedico, int edadMedico, String direccionMedico,
			String institucionMedico, String estadoMedico, Veterinaria veterinariaMedico) {
		super();
		this.idMedico = idMedico;
		this.nombreMedico = nombreMedico;
		this.dniMedico = dniMedico;
		this.edadMedico = edadMedico;
		this.direccionMedico = direccionMedico;
		this.institucionMedico = institucionMedico;
		this.estadoMedico = estadoMedico;
		this.veterinariaMedico = veterinariaMedico;
	}

	public Medico() {
		super();
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public String getDniMedico() {
		return dniMedico;
	}

	public void setDniMedico(String dniMedico) {
		this.dniMedico = dniMedico;
	}

	public int getEdadMedico() {
		return edadMedico;
	}

	public void setEdadMedico(int edadMedico) {
		this.edadMedico = edadMedico;
	}

	public String getDireccionMedico() {
		return direccionMedico;
	}

	public void setDireccionMedico(String direccionMedico) {
		this.direccionMedico = direccionMedico;
	}

	public String getInstitucionMedico() {
		return institucionMedico;
	}

	public void setInstitucionMedico(String institucionMedico) {
		this.institucionMedico = institucionMedico;
	}

	public String getEstadoMedico() {
		return estadoMedico;
	}

	public void setEstadoMedico(String estadoMedico) {
		this.estadoMedico = estadoMedico;
	}

	public Veterinaria getVeterinariaMedico() {
		return veterinariaMedico;
	}

	public void setVeterinariaMedico(Veterinaria veterinariaMedico) {
		this.veterinariaMedico = veterinariaMedico;
	}

}
