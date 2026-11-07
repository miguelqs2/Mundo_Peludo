package mvc.mundo.peludo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_items")
public class ItemCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Integer cantidad;
    
    // NUEVO CAMPO
    private Integer peso; 

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    
    // NUEVOS GETTER Y SETTER
    public Integer getPeso() { return peso; }
    public void setPeso(Integer peso) { this.peso = peso; }
}