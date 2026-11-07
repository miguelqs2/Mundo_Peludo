package mvc.mundo.peludo.controllers;

import mvc.mundo.peludo.models.Usuario;
import mvc.mundo.peludo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Mostrar formulario de Login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Mostrar formulario de Registro
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Procesar el Registro
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, @RequestParam String confirmPassword, Model model) {
        
        // Validar que las contraseñas coincidan
        if (!usuario.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
            return "registro";
        }

        // Validar que el correo no esté ya registrado
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            model.addAttribute("error", "Este correo ya está registrado.");
            return "registro";
        }

        // Encriptar contraseña y asignar rol
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol("ROLE_CLIENTE");
        
        usuarioRepository.save(usuario);
        
        // Redirigir al login con un mensaje de éxito
        return "redirect:/login?registrado=true";
    }
}