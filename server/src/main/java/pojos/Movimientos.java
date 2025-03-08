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

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	@NotNull(message = "El usuario es obligatorio")
	@JsonBackReference // Faltaba esto
	private Usuarios usuario;

	@ManyToOne
	@JoinColumn(name = "id_producto", nullable = false)
	@NotNull(message = "El producto es obligatorio")
	@JsonBackReference
	private Productos producto;

	@ManyToOne
	@JoinColumn(name = "id_albaran", nullable = false)
	@NotNull(message = "El albarán es obligatorio")
	@JsonBackReference 
	private Albaranes albaran;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private TipoMovimiento tipo;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "fecha_movimiento", nullable = false)
    private Timestamp fechaMovimiento;


    public enum TipoMovimiento {
        Alta,
        Baja
    }

	public Movimientos() {
	}

	public Movimientos(Usuarios usuarios, Productos productos, Albaranes albaranes, TipoMovimiento tipo, int cantidad,
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

	public Usuarios getUsuarios() {
		return this.usuario;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuario = usuarios;
	}

	public Productos getProductos() {
		return this.producto;
	}

	public void setProductos(Productos productos) {
		this.producto = productos;
	}

	public Albaranes getAlbaranes() {
		return this.albaran;
	}

	public void setAlbaranes(Albaranes albaranes) {
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
