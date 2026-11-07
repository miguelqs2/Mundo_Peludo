package mvc.mundo.peludo;

import mvc.mundo.peludo.models.Usuario;
import mvc.mundo.peludo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscamos al usuario en la base de datos por su correo
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        // Imprimimos en consola para estar seguros de que lo encuentra
        System.out.println("=== LOGIN INTENTO ===");
        System.out.println("Usuario encontrado: " + usuario.getEmail());
        System.out.println("Rol en BD: " + usuario.getRol());

        // Le decimos a Spring cómo validar al usuario
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getRol().replace("ROLE_", "")) 
                .build();
    }
}