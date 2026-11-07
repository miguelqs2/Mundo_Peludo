package mvc.mundo.peludo.repositories;

import mvc.mundo.peludo.models.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<ItemCarrito, Long> {
    // Método para buscar un item por el ID de su producto
    Optional<ItemCarrito> findByProductoId(Long productoId);
}