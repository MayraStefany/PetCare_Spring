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
@Table(name = "cliente")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;

	@NotEmpty(message = "Ingresa el nombre del Cliente")
	@Column(name = "nombre", nullable = false, length = 40)
	private String nombreCliente;

	@NotEmpty(message = "Ingresa el apellido del Cliente")
	@Column(name = "apellido", nullable = false, length = 40)
	private String apellidoCliente;

	@NotEmpty(message = "Ingresar la direccion en la que se encuentra la Cliente")
	@Column(name = "direccion", nullable = false, length = 70)
	private String direccionCliente;

	@Pattern(regexp = "[\\d]{9}", message = "El n\u00famero de contacto tiene que ser de 9 d\u00edgitos y no puede ingresar letras")
	@NotEmpty(message = "Ingresa el numero de telefono del alumno")
	@Column(name = "telefono", nullable = false, length = 9)
	private String telefonoCliente;

	@Size(min = 8, max = 8, message = "EL DNI tiene que ser de 11 d\u00edgitos")
	@NotEmpty(message = "Ingresa el DNI del Cliente")
	@Column(name = "dni", nullable = false, length = 11)
	private String dniCliente;

	@Min(0)
	@Max(99)
	@Column(name = "edad", nullable = false)
	private int edadCliente;

	@NotEmpty(message = "Ingresa el genero del Cliente")
	@Column(name = "genero", nullable = false, length = 20)
	private String generoCliente;

	@NotEmpty(message = "Ingresar el correo del Cliente")
	@Column(name = "correo", nullable = false, length = 70)
	private String correoCliente;

	public Cliente(int idCliente, String nombreCliente, String apellidoCliente, String direccionCliente,
			String telefonoCliente, String dniCliente, int edadCliente, String generoCliente, String correoCliente) {
		super();
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.direccionCliente = direccionCliente;
		this.telefonoCliente = telefonoCliente;
		this.dniCliente = dniCliente;
		this.edadCliente = edadCliente;
		this.generoCliente = generoCliente;
		this.correoCliente = correoCliente;
	}

	public Cliente() {
		super();
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public int getEdadCliente() {
		return edadCliente;
	}

	public void setEdadCliente(int edadCliente) {
		this.edadCliente = edadCliente;
	}

	public String getGeneroCliente() {
		return generoCliente;
	}

	public void setGeneroCliente(String generoCliente) {
		this.generoCliente = generoCliente;
	}

	public String getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

}
