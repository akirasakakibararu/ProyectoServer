package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pojos.Usuarios;
import service.UserService;

@RestController
@RequestMapping("/usuarios")
public class UserController {
	@Autowired
	private UserService usuarioService;

	@GetMapping
	public List<Usuarios> listarUsuarios() {
		return usuarioService.obtenerTodos();
	}

	@GetMapping("/{email}")
	public Usuarios buscarPorEmail(@PathVariable String email) {
		return usuarioService.obtenerPorEmail(email);
	}

	@PostMapping
	public Usuarios crearUsuario(@RequestBody Usuarios usuario) {
		return usuarioService.guardar(usuario);
	}

	@GetMapping("/test")
	public String test() {
		return "UserController funciona correctamente!";
	}
	 @PutMapping("/{id}")
	    public ResponseEntity<Usuarios> actualizarUsuario(@PathVariable int id, @RequestBody Usuarios usuario) {
		 usuario.setIdUsuario(id);
	        Usuarios userActualizado = usuarioService.actualizarUsuario(usuario);
	        return ResponseEntity.ok(userActualizado);
	    }
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
		 usuarioService.eliminarUsuario(id);
	        return ResponseEntity.noContent().build();
	    }
}