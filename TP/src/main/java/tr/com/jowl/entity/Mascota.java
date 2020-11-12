package tr.com.jowl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "mascota")
public class Mascota implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMascota;

	@NotEmpty(message = "Ingresar el nombre de la mascota")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombreMascota;

	@NotEmpty(message = "Ingresar la raza de la mascota")
	@Column(name = "raza", nullable = false, length = 40)
	private String razaMascota;

	@Pattern(regexp = "[\\d]{1,2}", message = "La edad tiene que ser menor a 99 años y no puede ingresar letras")
	@NotEmpty(message = "Ingresar la edad de la mascota")
	@Column(name = "edad", nullable = false, length = 3)
	private String edadMascota;

	public Mascota(int idMascota, String nombreMascota, String razaMascota, String edadMascota) {
		super();
		this.idMascota = idMascota;
		this.nombreMascota = nombreMascota;
		this.razaMascota = razaMascota;
		this.edadMascota = edadMascota;
	}

	public Mascota() {
		super();
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public String getNombreMascota() {
		return nombreMascota;
	}

	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}

	public String getRazaMascota() {
		return razaMascota;
	}

	public void setRazaMascota(String razaMascota) {
		this.razaMascota = razaMascota;
	}

	public String getEdadMascota() {
		return edadMascota;
	}

	public void setEdadMascota(String edadMascota) {
		this.edadMascota = edadMascota;
	}

}
