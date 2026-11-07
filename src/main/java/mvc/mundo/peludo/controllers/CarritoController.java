package mvc.mundo.peludo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mvc.mundo.peludo.models.ItemCarrito;
import mvc.mundo.peludo.models.Pedido;
import mvc.mundo.peludo.models.Producto;
import mvc.mundo.peludo.models.Usuario;
import mvc.mundo.peludo.repositories.CarritoRepository;
import mvc.mundo.peludo.repositories.PedidoRepository;
import mvc.mundo.peludo.repositories.ProductoRepository;
import mvc.mundo.peludo.repositories.UsuarioRepository;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired private CarritoRepository carritoRepository;
    @Autowired private ProductoRepository productoRepository;
    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @GetMapping
    public String verCarrito(Model model, Authentication auth) {
        List<ItemCarrito> items = carritoRepository.findAll();
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        
        boolean descuento = false;
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            Usuario u = usuarioRepository.findByEmail(auth.getName()).orElse(null);
            if (u != null && Boolean.FALSE.equals(u.getDescuentoAplicado())) {
                descuento = true;
                total = total * 0.85; // Aplicamos el 15% de descuento al total
            }
        }
        
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        model.addAttribute("aplicaDescuento", descuento); // Para mostrar un mensajito en el carrito
        return "carrito";
    }

    @PostMapping("/agregar")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> agregarAlCarrito(
            @RequestParam Long id,
            @RequestParam(defaultValue = "0") int peso) {

        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            List<ItemCarrito> todos = carritoRepository.findAll();
            ItemCarrito itemExistente = null;
            for (ItemCarrito i : todos) {
                if (i.getProducto().getId().equals(id) && 
                    ((peso == 0 && i.getPeso() == null) || (i.getPeso() != null && i.getPeso() == peso))) {
                    itemExistente = i; break;
                }
            }

            if (itemExistente != null) {
                itemExistente.setCantidad(itemExistente.getCantidad() + 1);
                carritoRepository.save(itemExistente);
            } else {
                ItemCarrito nuevo = new ItemCarrito();
                nuevo.setProducto(producto);
                nuevo.setCantidad(1);
                nuevo.setPeso(peso > 0 ? peso : null);
                carritoRepository.save(nuevo);
            }
        }

        long totalItems = carritoRepository.findAll().stream().mapToLong(i -> i.getCantidad()).sum();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("totalItems", totalItems);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sumar/{id}")
    public String sumarCantidad(@PathVariable Long id) {
        carritoRepository.findById(id).ifPresent(item -> {
            item.setCantidad(item.getCantidad() + 1);
            carritoRepository.save(item);
        });
        return "redirect:/carrito";
    }

    @GetMapping("/restar/{id}")
    public String restarCantidad(@PathVariable Long id) {
        carritoRepository.findById(id).ifPresent(item -> {
            if (item.getCantidad() > 1) {
                item.setCantidad(item.getCantidad() - 1);
                carritoRepository.save(item);
            } else {
                carritoRepository.delete(item);
            }
        });
        return "redirect:/carrito";
    }
    
    @GetMapping("/finalizar")
    public String finalizarCompra(Authentication auth) {
        List<ItemCarrito> items = carritoRepository.findAll();
        
        if (!items.isEmpty()) {
            double total = 0;
            for (ItemCarrito item : items) {
                total += item.getProducto().getPrecio() * item.getCantidad();
            }

            // Verificamos si el usuario tiene descuento y lo aplicamos al pedido final
            if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
                Usuario u = usuarioRepository.findByEmail(auth.getName()).orElse(null);
                if (u != null && Boolean.FALSE.equals(u.getDescuentoAplicado())) {
                    total = total * 0.85;
                    u.setDescuentoAplicado(true); // Ya usó su descuento
                    usuarioRepository.save(u);
                }
            }

            Pedido nuevoPedido = new Pedido();
            nuevoPedido.setFecha(java.time.LocalDateTime.now());
            nuevoPedido.setTotal(total);
            nuevoPedido.setEstado("Preparando");
            pedidoRepository.save(nuevoPedido);

            for (ItemCarrito item : items) {
                carritoRepository.delete(item);
            }
        }
        return "redirect:/carrito/gracias";
    }

    @GetMapping("/gracias")
    public String mostrarGracias() {
        return "gracias"; 
    }
}