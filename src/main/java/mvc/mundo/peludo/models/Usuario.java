package mvc.mundo.peludo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String nombre;
    private String rol; 
    
    // NUEVO CAMPO: Para saber si ya usó su cupón de bienvenida
    private Boolean descuentoAplicado = false;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    
    public Boolean getDescuentoAplicado() { return descuentoAplicado; }
    public void setDescuentoAplicado(Boolean descuentoAplicado) { this.descuentoAplicado = descuentoAplicado; }
}