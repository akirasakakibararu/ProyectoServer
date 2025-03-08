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

	@ManyToOne
	@JoinColumn(name = "id_proveedor", nullable = false)
	@JsonBackReference
	private Proveedores proveedor;

	@Column(name = "nombre", nullable = false, length = 100)
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@Column(name = "descripcion", columnDefinition = "TEXT")
	private String descripcion;

	@Lob
	@Column(name = "foto")
	@JsonIgnore
	private byte[] foto;

	@Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
	@NotNull(message = "El precio unitario es obligatorio")
	private BigDecimal precioUnitario;

	@Column(name = "stock_actual", nullable = false)
	private int stockActual;

	@Column(name = "stock_minimo", nullable = false)
	private int stockMinimo;

	@Column(name = "habilitado", columnDefinition = "TINYINT(1)")
	private boolean habilitado = true;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Set<Movimientos> movimientos = new HashSet<>();

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Set<DetallesAlbaran> detallesAlbaran = new HashSet<>();

	public Productos() {
	}

	public Productos(Proveedores proveedor, String nombre, BigDecimal precioUnitario, int stockActual,
			int stockMinimo) {
		this.proveedor = proveedor;
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
	}

	public Productos(Proveedores proveedor, String nombre, String descripcion, byte[] foto, BigDecimal precioUnitario,
			int stockActual, int stockMinimo, boolean habilitado, Set<Movimientos> movimientos,
			Set<DetallesAlbaran> detallesAlbaran) {
		this.proveedor = proveedor;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.foto = foto;
		this.precioUnitario = precioUnitario;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
		this.habilitado = habilitado;
		this.movimientos = movimientos;
		this.detallesAlbaran = detallesAlbaran;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public Proveedores getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedores proveedor) {
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
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

	public Set<Movimientos> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(Set<Movimientos> movimientos) {
		this.movimientos = movimientos;
	}

	public Set<DetallesAlbaran> getDetallesAlbaran() {
		return detallesAlbaran;
	}

	public void setDetallesAlbaran(Set<DetallesAlbaran> detallesAlbaran) {
		this.detallesAlbaran = detallesAlbaran;
	}
}