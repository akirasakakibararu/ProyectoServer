package pojos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int idUsuario;

	@Column(nullable = false, length = 255)
	private String contrasena;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(nullable = false, length = 100, unique = true)
	private String email;

	@Column(name = "foto_perfil")
	private String fotoPerfil;

	@Column(name = "habilitado")
	private boolean habilitado = true;

	@Enumerated(EnumType.STRING)
	@Column(name = "rol", nullable = false)
	private Rol rol;

	public enum Rol {
		Empleado, Administrador
	}

	public Usuarios() {

	}

	public Usuarios(String nombre, String contrasena) {
		this.nombre = nombre;
		this.contrasena = contrasena;
	}

	public Usuarios( String nombre, String contrasena,String email, Rol rol) {
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.email = email;
		this.rol = rol;

	}

	public Usuarios(String contrasena, String nombre, String email, String fotoPerfil, Rol rol, boolean habilitado) {
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.email = email;
		this.fotoPerfil = fotoPerfil;
		this.habilitado = habilitado;
		this.rol = rol;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}