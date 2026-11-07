package mvc.mundo.peludo.repositories;

import mvc.mundo.peludo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar al usuario por su correo al iniciar sesión
    Optional<Usuario> findByEmail(String email);
}