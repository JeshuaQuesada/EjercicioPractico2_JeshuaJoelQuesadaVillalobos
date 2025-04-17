/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2.EjercicioPractico2.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;


//es para las funciones
@Data
@Entity
@Table(name="funciones")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id_Pelicula;
    //private Long idCategoria;  ya no se usa por el @manyToOne
    private String fecha;
    private String hora;
    private double precio;
    private String sala;
    private boolean activo;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    Categoria categoria;


    public Producto() {
    }

    public Producto(Long id_Pelicula, boolean activo) {
        this.id_Pelicula = id_Pelicula;
        this.activo = activo;
    }
    
}