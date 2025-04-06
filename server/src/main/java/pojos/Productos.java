package pojos;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
public class Productos implements java.io.Serializable {

	private static final long serialVersionUID = 445403171441690784L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;

	@Column(name = "id_proveedor", nullable = false)
	private int proveedor;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "descripcion", columnDefinition = "TEXT")
	private String descripcion;

	
	@Column(name = "foto")
	private String foto;

	@Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)

	private BigDecimal precioUnitario;

	@Column(name = "stock_actual", nullable = false)
	private int stockActual;

	@Column(name = "stock_minimo", nullable = false)
	private int stockMinimo;

	@Column(name = "habilitado", columnDefinition = "TINYINT(1)")
	private boolean habilitado = true;

	public Productos() {
	}

	public Productos(int proveedor, String nombre, BigDecimal precioUnitario, int stockActual, int stockMinimo) {
		this.proveedor = proveedor;
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
	}

	public Productos(int proveedor, String nombre, String descripcion, String foto, BigDecimal precioUnitario,
			int stockActual, int stockMinimo, boolean habilitado) {
		this.proveedor = proveedor;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.foto = foto;
		this.precioUnitario = precioUnitario;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
		this.habilitado = habilitado;

	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getProveedor() {
		return proveedor;
	}

	public void setProveedor(int proveedor) {
		this.proveedor = proveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getStockActual() {
		return stockActual;
	}

	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

}