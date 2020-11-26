package tr.com.jowl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "medicamento")
public class Medicamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMedicamento;

	@NotEmpty(message = "Ingresar el nombre del medicamento")
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombreMedicamento;

	@NotEmpty(message = "Ingresar el tipo de medicamento")
	@Column(name = "tipo", nullable = false, length = 50)
	private String tipoMedicamento;

	private Date fechaMedicamento;

	@NotEmpty(message = "Ingresar el laboratorio de desarrollo del medicamento")
	@Column(name = "laboratorio", nullable = false, length = 50)
	private String laboratorioMedicamento;

	public Medicamento(int idMedicamento,
			@NotEmpty(message = "Ingresar el nombre del medicamento") String nombreMedicamento,
			@NotEmpty(message = "Ingresar el tipo de medicamento") String tipoMedicamento, Date fechaMedicamento,
			@NotEmpty(message = "Ingresar el laboratorio de desarrollo del medicamento") String laboratorioMedicamento) {
		super();
		this.idMedicamento = idMedicamento;
		this.nombreMedicamento = nombreMedicamento;
		this.tipoMedicamento = tipoMedicamento;
		this.fechaMedicamento = fechaMedicamento;
		this.laboratorioMedicamento = laboratorioMedicamento;
	}

	public Medicamento() {
		super();
	}

	public int getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getNombreMedicamento() {
		return nombreMedicamento;
	}

	public void setNombreMedicamento(String nombreMedicamento) {
		this.nombreMedicamento = nombreMedicamento;
	}

	public String getTipoMedicamento() {
		return tipoMedicamento;
	}

	public void setTipoMedicamento(String tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}

	public Date getFechaMedicamento() {
		return fechaMedicamento;
	}

	public void setFechaMedicamento(Date fechaMedicamento) {
		this.fechaMedicamento = fechaMedicamento;
	}

	public String getLaboratorioMedicamento() {
		return laboratorioMedicamento;
	}

	public void setLaboratorioMedicamento(String laboratorioMedicamento) {
		this.laboratorioMedicamento = laboratorioMedicamento;
	}

}
