package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.server.UserRepository;

import pojos.Usuarios;
import pojos.Usuarios.Rol;
import security.JwtUtil;
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRe;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public List<Usuarios> obtenerTodos() {
        return userRe.findAll();
    }

    public Usuarios obtenerPorEmail(String email) {
        return userRe.findByEmail(email);
    }

    public Usuarios obtenerPorNombre(String nombre) {
        return userRe.findByNombre(nombre);
    }

    public Usuarios guardar(Usuarios usuario) {
        return userRe.save(usuario);
    }
    public String registerUser(String username, String password, String email,Rol rol) {
        // Verificar si el usuario ya existe
        if (userRe.findByNombre(username) != null) {
            throw new RuntimeException("El usuario ya existe");
        }

        // Encriptar la contraseña
        String encodedPassword = passwordEncoder.encode(password);

        // Crear el usuario correctamente
        Usuarios nuevoUsuario = new Usuarios(username, encodedPassword, email,rol);
        userRe.save(nuevoUsuario);

        return "Usuario registrado con éxito";
    }

    public String authenticateUser(String username, String password) {
        Usuarios usuario = userRe.findByNombre(username);
        System.out.println(username + " === " + password);
        
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado"+password);
        }

        // Comparar la contraseña en texto plano con la contraseña encriptada almacenada
        if (!passwordEncoder.matches(password, usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Crear UserDetails correctamente
        UserDetails userDetails = new User(username, usuario.getContrasena(), new java.util.ArrayList<>());

        // Generar token JWT
        return jwtUtil.generateToken(userDetails.getUsername());
    }

}