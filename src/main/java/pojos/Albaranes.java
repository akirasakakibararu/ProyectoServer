package pojos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "albaranes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Albaranes implements java.io.Serializable {

    private static final long serialVersionUID = -103313744362316324L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_albaran")
    private int idAlbaran;

    @Lob
    @Column(name = "foto", columnDefinition = "BLOB")
    private String fotoAlbaran;

    @Column(name = "nif", nullable = false, length = 50)
    private String nif;

    @Column(name = "fecha", nullable = false)
    private Timestamp fechaAlbaran;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", columnDefinition = "enum('Pendiente','Pagado')")
    private EstadoAlbaran estado;

    public enum EstadoAlbaran {
        Pendiente, Pagado
    }

    @OneToMany(mappedBy = "albaranEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<DetallesAlbaran> detalles = new ArrayList<>();

    public Albaranes() {}

    public Albaranes(String nif, Timestamp fechaAlbaran) {
        this.nif = nif;
        this.fechaAlbaran = fechaAlbaran;
    }

    public Albaranes(String nif, Timestamp fechaAlbaran, String fotoAlbaran, EstadoAlbaran estado) {
        this.nif = nif;
        this.fechaAlbaran = fechaAlbaran;
        this.fotoAlbaran = fotoAlbaran;
        this.estado = estado;
    }

    public int getIdAlbaran() {
        return idAlbaran;
    }

    public void setIdAlbaran(int idAlbaran) {
        this.idAlbaran = idAlbaran;
    }

    public String getFotoAlbaran() {
        return fotoAlbaran;
    }

    public void setFotoAlbaran(String fotoAlbaran) {
        this.fotoAlbaran = fotoAlbaran;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Timestamp getFechaAlbaran() {
        return fechaAlbaran;
    }

    public void setFechaAlbaran(Timestamp fechaAlbaran) {
        this.fechaAlbaran = fechaAlbaran;
    }

    public EstadoAlbaran getEstado() {
        return estado;
    }

    public void setEstado(EstadoAlbaran estado) {
        this.estado = estado;
    }

    public List<DetallesAlbaran> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallesAlbaran> detalles) {
        this.detalles = detalles;
    }

    public BigDecimal getTotal() {
        return detalles.stream()
            .map(d -> d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
