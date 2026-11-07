package mvc.mundo.peludo.controllers;

import mvc.mundo.peludo.models.Producto;
import mvc.mundo.peludo.models.Usuario;
import mvc.mundo.peludo.repositories.CarritoRepository;
import mvc.mundo.peludo.repositories.UsuarioRepository;
import mvc.mundo.peludo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository; // Para buscar al usuario logueado

    // Método auxiliar para verificar si aplica descuento
    private boolean aplicaDescuento(Authentication auth) {
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            Usuario u = usuarioRepository.findByEmail(auth.getName()).orElse(null);
            return u != null && Boolean.FALSE.equals(u.getDescuentoAplicado());
        }
        return false;
    }

    @GetMapping("/")
    public String inicio(Model model, Authentication auth) {
        List<Producto> productos = productoService.obtenerTodos();
        
        boolean descuento = aplicaDescuento(auth);
        model.addAttribute("aplicaDescuento", descuento); // Enviamos true o false
        model.addAttribute("productos", productos);
        model.addAttribute("totalCarrito", carritoRepository.findAll().stream().mapToLong(i -> i.getCantidad()).sum());
        model.addAttribute("categoriaSeleccionada", null); 
        return "index";
    }

    @GetMapping("/categoria/{nombre}")
    public String filtrarPorCategoria(@PathVariable String nombre, Model model, Authentication auth) {
        List<Producto> filtrados = productoService.obtenerTodos().stream()
                .filter(p -> p.getCategoria() != null && p.getCategoria().getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());

        boolean descuento = aplicaDescuento(auth);
        model.addAttribute("aplicaDescuento", descuento);
        model.addAttribute("productos", filtrados);
        model.addAttribute("totalCarrito", carritoRepository.findAll().stream().mapToLong(i -> i.getCantidad()).sum());
        model.addAttribute("categoriaSeleccionada", nombre);
        return "index"; 
    }

    @GetMapping("/buscar")
    public String buscarProductos(@RequestParam String query, Model model, Authentication auth) {
        List<Producto> resultados = productoService.obtenerTodos().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());

        boolean descuento = aplicaDescuento(auth);
        model.addAttribute("aplicaDescuento", descuento);
        model.addAttribute("productos", resultados);
        model.addAttribute("totalCarrito", carritoRepository.findAll().stream().mapToLong(i -> i.getCantidad()).sum());
        model.addAttribute("categoriaSeleccionada", "Búsqueda: '" + query + "'"); 
        return "index"; 
    }
}