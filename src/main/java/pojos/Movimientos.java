package pojos;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "movimientos")
public class Movimientos implements java.io.Serializable {

	private static final long serialVersionUID = -5328273247962656318L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovimiento;

	@Column(name = "id_usuario", nullable = false)

	private int usuario;

	@Column(name = "id_producto", nullable = false)

	private int producto;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 100)
	private TipoMovimiento tipo;

	@Column(name = "fecha_movimiento", nullable = false)
	private Timestamp fechaMovimiento;

	public enum TipoMovimiento {
		Mas, Menos
	}

	public Movimientos() {
	}

	public Movimientos(int usuarios, int productos, TipoMovimiento tipo, Timestamp fechaMovimiento) {
		this.usuario = usuarios;
		this.producto = productos;

		this.tipo = tipo;

		this.fechaMovimiento = fechaMovimiento;
	}

	public Integer getIdMovimiento() {
		return this.idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public int getUsuarios() {
		return this.usuario;
	}

	public void setUsuarios(int usuarios) {
		this.usuario = usuarios;
	}

	public int getProductos() {
		return this.producto;
	}

	public void setProductos(int productos) {
		this.producto = productos;
	}

	public TipoMovimiento getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	public Timestamp getFechaMovimiento() {
		return this.fechaMovimiento;
	}

	public void setFechaMovimiento(Timestamp fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

}