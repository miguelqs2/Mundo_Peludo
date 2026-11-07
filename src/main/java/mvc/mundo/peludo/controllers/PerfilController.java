package mvc.mundo.peludo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mvc.mundo.peludo.models.Pedido;
import mvc.mundo.peludo.models.Usuario;
import mvc.mundo.peludo.repositories.PedidoRepository;
import mvc.mundo.peludo.repositories.UsuarioRepository;

@Controller
public class PerfilController {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/perfil")
    public String verPerfil(Model model, Authentication authentication) {
        // 1. Obtenemos el correo del usuario logueado
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);

        // 2. Buscamos sus pedidos y calculamos puntos
        List<Pedido> pedidos = pedidoRepository.findAll();
        double totalGastado = 0;
        for (Pedido p : pedidos) {
            totalGastado += p.getTotal();
        }
        int puntos = (int) (totalGastado / 10);

        // 3. Enviamos los datos reales a la vista
        model.addAttribute("usuario", usuario);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("puntos", puntos);
        
        return "perfil";
    }
}