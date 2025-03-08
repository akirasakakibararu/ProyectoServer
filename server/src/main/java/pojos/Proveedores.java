package pojos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "proveedores")
public class Proveedores implements Serializable {

	private static final long serialVersionUID = -6765990520995465267L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProveedor;

    @Column(name = "nombre", nullable = false, length = 255)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "email", length = 100, unique = true)
    @Email(message = "El email debe ser válido")
    private String email;

    @Column(name = "nif", length = 50, unique = true)
    @NotNull(message = "El NIF es obligatorio")
    private String nif;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Productos> productos = new HashSet<>();


    public Proveedores() {
    }

    public Proveedores(String nombre) {
        this.nombre = nombre;
    }

    public Proveedores(String nombre, String telefono, String direccion, String email, String nif) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.nif = nif;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Set<Productos> getProductos() {
        return productos;
    }

    public void setProductos(Set<Productos> productos) {
        this.productos = productos;
    }
}