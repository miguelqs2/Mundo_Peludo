package mvc.mundo.peludo.controllers;

import mvc.mundo.peludo.models.Producto;
import mvc.mundo.peludo.repositories.CategoriaRepository;
import mvc.mundo.peludo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String panelAdmin() {
        return "redirect:/admin/nuevo-producto";
    }

    @GetMapping("/nuevo-producto")
    public String mostrarFormulario(@RequestParam(required = false) Boolean exito, Model model) {
        model.addAttribute("producto", new Producto()); // Formulario limpio
        model.addAttribute("categorias", categoriaRepository.findAll());
        
        // Si venimos de guardar un producto, enviamos la bandera de éxito
        if (exito != null && exito) {
            model.addAttribute("mensajeExito", "¡Producto guardado exitosamente!");
        }
        
        return "admin-form"; 
    }

    @PostMapping("/guardar-producto")
    public String guardarProducto(@ModelAttribute Producto producto) {
        
        // Validaciones de seguridad
        if (producto.getPrecio() == null || producto.getPrecio() < 0) {
            producto.setPrecio(0.0);
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            producto.setStock(0);
        }
        if (producto.getImagenUrl() == null || producto.getImagenUrl().isEmpty()) {
            producto.setImagenUrl("https://images.unsplash.com/photo-1583337130417-3346a1be7dee?w=400");
        }

        productoService.guardar(producto);
        
        // REDIRIGE AL MISMO FORMULARIO CON LA BANDERA DE ÉXITO
        return "redirect:/admin/nuevo-producto?exito=true"; 
    }
}