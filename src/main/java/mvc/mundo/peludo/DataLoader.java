package mvc.mundo.peludo;

import mvc.mundo.peludo.models.Categoria;
import mvc.mundo.peludo.models.Producto;
import mvc.mundo.peludo.repositories.CategoriaRepository;
import mvc.mundo.peludo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productoRepository.count() == 0) {
            
            // 1. Creamos las categorías
            Categoria alimentos = new Categoria(); alimentos.setNombre("Alimentos"); categoriaRepository.save(alimentos);
            Categoria juguetes = new Categoria(); juguetes.setNombre("Juguetes"); categoriaRepository.save(juguetes);
            Categoria accesorios = new Categoria(); accesorios.setNombre("Accesorios"); categoriaRepository.save(accesorios);
            Categoria higiene = new Categoria(); higiene.setNombre("Higiene"); categoriaRepository.save(higiene);

            // 2. Producto de Alimento (Solo uno)
            Producto dogChow = new Producto();
            dogChow.setNombre("Dog Chow Adultos 15 kg");
            dogChow.setDescripcion("Alimento completo y balanceado para perros adultos.");
            dogChow.setPrecio(79.90);
            dogChow.setImagenUrl("https://images.unsplash.com/photo-1589924691995-400dc9ecc119?w=400");
            dogChow.setStock(20);
            dogChow.setCategoria(alimentos);

            // 3. Producto de Juguete
            Producto pelota = new Producto();
            pelota.setNombre("Juguete Pelota para Perro");
            pelota.setDescripcion("Pelota resistente de caucho.");
            pelota.setPrecio(9.90);
            pelota.setImagenUrl("https://images.unsplash.com/photo-1576201836106-db1758fd1c97?w=400");
            pelota.setStock(50);
            pelota.setCategoria(juguetes);

            productoRepository.save(dogChow);
            productoRepository.save(pelota);
            
            System.out.println("¡Categorías y productos insertados correctamente!");
        }
    }
}