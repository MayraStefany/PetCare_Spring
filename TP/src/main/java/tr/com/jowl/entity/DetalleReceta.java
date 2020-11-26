package tr.com.jowl.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detallereceta")
public class DetalleReceta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalle;

	@ManyToOne
	@JoinColumn(name = "idMedicamento")
	private Medicamento medicamentoDetalle;

	@ManyToOne
	@JoinColumn(name = "idReceta")
	private Receta recetaDetalle;

	private int precioDetalle;

	public DetalleReceta(int idDetalle, Medicamento medicamentoDetalle, Receta recetaDetalle, int precioDetalle) {
		super();
		this.idDetalle = idDetalle;
		this.medicamentoDetalle = medicamentoDetalle;
		this.recetaDetalle = recetaDetalle;
		this.precioDetalle = precioDetalle;
	}

	public DetalleReceta() {
		super();
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Medicamento getMedicamentoDetalle() {
		return medicamentoDetalle;
	}

	public void setMedicamentoDetalle(Medicamento medicamentoDetalle) {
		this.medicamentoDetalle = medicamentoDetalle;
	}

	public Receta getRecetaDetalle() {
		return recetaDetalle;
	}

	public void setRecetaDetalle(Receta recetaDetalle) {
		this.recetaDetalle = recetaDetalle;
	}

	public int getPrecioDetalle() {
		return precioDetalle;
	}

	public void setPrecioDetalle(int precioDetalle) {
		this.precioDetalle = precioDetalle;
	}

}
