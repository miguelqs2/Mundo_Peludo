package mvc.mundo.peludo.services;

import mvc.mundo.peludo.models.Producto;
import mvc.mundo.peludo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
    
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }
}