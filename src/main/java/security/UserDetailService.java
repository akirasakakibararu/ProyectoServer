package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import repository.UserRepository;
import pojos.Usuarios;

import java.util.List;
 @Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = userRepository.findByNombre(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // Devuelves un objeto User (de Spring Security), no el objeto Usuarios
        return new org.springframework.security.core.userdetails.User(
            usuario.getNombre(),
            usuario.getContrasena(),
            List.of(new SimpleGrantedAuthority("ROLE_USER")) // Asegúrate de agregar al menos un rol
        );
    }
}
