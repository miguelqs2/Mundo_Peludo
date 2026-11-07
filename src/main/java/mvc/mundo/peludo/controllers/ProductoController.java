package mvc.mundo.peludo.controllers;

import mvc.mundo.peludo.models.Producto;
import mvc.mundo.peludo.models.Usuario;
import mvc.mundo.peludo.repositories.CarritoRepository;
import mvc.mundo.peludo.repositories.ProductoRepository;
import mvc.mundo.peludo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoRepository carritoRepository; 
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Long id, Model model, Authentication auth) {
        Optional<Producto> producto = productoRepository.findById(id);
        
        if (producto.isPresent()) {
            boolean descuento = false;
            if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
                Usuario u = usuarioRepository.findByEmail(auth.getName()).orElse(null);
                if (u != null && Boolean.FALSE.equals(u.getDescuentoAplicado())) {
                    descuento = true;
                }
            }

            model.addAttribute("producto", producto.get());
            model.addAttribute("aplicaDescuento", descuento);
            
            long total = carritoRepository.findAll().stream().mapToLong(i -> i.getCantidad()).sum();
            model.addAttribute("totalCarrito", total);
            
            return "producto";
        }
        return "redirect:/";
    }
}