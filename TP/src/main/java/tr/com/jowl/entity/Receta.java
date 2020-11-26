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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "receta")
public class Receta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReceta;

	@ManyToOne
	@JoinColumn(name = "idAtencion")
	private Atencion atencionReceta;

	@NotEmpty(message = "Ingresa la descripcion del Medicamento")
	@Column(name = "descripcion", nullable = false, length = 60)
	private String descripcionReceta;

	@Min(0)
	@Column(name = "totalReceta", nullable = false)
	private int totalReceta;

	@Min(0)
	@Column(name = "cantidadReceta", nullable = false)
	private int cantidadReceta;

	public Receta(int idReceta, Atencion atencionReceta, String descripcionReceta, int totalReceta,
			int cantidadReceta) {
		super();
		this.idReceta = idReceta;
		this.atencionReceta = atencionReceta;
		this.descripcionReceta = descripcionReceta;
		this.totalReceta = totalReceta;
		this.cantidadReceta = cantidadReceta;
	}

	public Receta() {
		super();
	}

	public int getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(int idReceta) {
		this.idReceta = idReceta;
	}

	public Atencion getAtencionReceta() {
		return atencionReceta;
	}

	public void setAtencionReceta(Atencion atencionReceta) {
		this.atencionReceta = atencionReceta;
	}

	public String getDescripcionReceta() {
		return descripcionReceta;
	}

	public void setDescripcionReceta(String descripcionReceta) {
		this.descripcionReceta = descripcionReceta;
	}

	public int getTotalReceta() {
		return totalReceta;
	}

	public void setTotalReceta(int totalReceta) {
		this.totalReceta = totalReceta;
	}

	public int getCantidadReceta() {
		return cantidadReceta;
	}

	public void setCantidadReceta(int cantidadReceta) {
		this.cantidadReceta = cantidadReceta;
	}

}
