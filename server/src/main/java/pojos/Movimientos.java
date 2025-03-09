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

	@Column(name = "id_albaran", nullable = false)
	private int albaran;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 100)
	private TipoMovimiento tipo;

	@Column(nullable = false)
	private int cantidad;

	@Column(name = "fecha_movimiento", nullable = false)
	private Timestamp fechaMovimiento;

	public enum TipoMovimiento {
		Alta, Baja
	}

	public Movimientos() {
	}

	public Movimientos(int usuarios, int productos, int albaranes, TipoMovimiento tipo, int cantidad,
			Timestamp fechaMovimiento) {
		this.usuario = usuarios;
		this.producto = productos;
		this.albaran = albaranes;
		this.tipo = tipo;
		this.cantidad = cantidad;
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

	public int getAlbaranes() {
		return this.albaran;
	}

	public void setAlbaranes(int albaranes) {
		this.albaran = albaranes;
	}

	public TipoMovimiento getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Timestamp getFechaMovimiento() {
		return this.fechaMovimiento;
	}

	public void setFechaMovimiento(Timestamp fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

}
