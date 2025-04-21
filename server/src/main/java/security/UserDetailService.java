package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.server.UserRepository;

import pojos.Usuarios;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos
        Usuarios usuario = userRepository.findByNombre(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // Convertir el usuario a UserDetails
        return new org.springframework.security.core.userdetails.User(
            usuario.getNombre(),
            usuario.getContrasena(),
            new java.util.ArrayList<>() // Aquí puedes agregar los roles del usuario si los tienes
        );
    }
}