package pojos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_albaran")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DetallesAlbaran implements java.io.Serializable {

    private static final long serialVersionUID = 3246065114967305279L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;

    @Column(name = "id_producto", nullable = false)
    private int producto;

    @Column(name = "id_albaran", nullable = false)
    private int albaran;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    // RELACIÓN CON ALBARANES
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_albaran", insertable = false, updatable = false)
    @JsonBackReference
    private Albaranes albaranEntity;

    // RELACIÓN CON PRODUCTO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Productos productoEntity;

    public DetallesAlbaran() {
    }

    public DetallesAlbaran(int producto, int albaran, BigDecimal precioUnitario, int cantidad) {
        this.producto = producto;
        this.albaran = albaran;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    // Getters y setters

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getAlbaran() {
        return albaran;
    }

    public void setAlbaran(int albaran) {
        this.albaran = albaran;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Albaranes getAlbaranEntity() {
        return albaranEntity;
    }

    public void setAlbaranEntity(Albaranes albaranEntity) {
        this.albaranEntity = albaranEntity;
    }

    public Productos getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(Productos productoEntity) {
        this.productoEntity = productoEntity;
    }

    // Subtotal
    public BigDecimal getSubtotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}
