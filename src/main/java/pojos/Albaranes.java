package pojos;

import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "albaranes")
public class Albaranes implements java.io.Serializable {

    private static final long serialVersionUID = -103313744362316324L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_albaran")
    private int idAlbaran;

    @Column(name = "foto")
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

    public Albaranes() {
    }

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

    public Integer getIdAlbaran() {
        return idAlbaran;
    }

    public void setIdAlbaran(Integer idAlbaran) {
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
}