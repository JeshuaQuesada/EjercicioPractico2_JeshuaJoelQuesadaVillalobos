/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2.EjercicioPractico2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Producto {
    private int cantidad; // Almacenar la cantidad de items de un producto

    public Item() {
    }

    public Item(Producto producto) {
        super.setIdProducto(producto.getId_Pelicula());
        super.setCategoria(producto.getCategoria());
        super.setDescripcion(producto.getFecha());
        super.setDetalle(producto.getHora());
        super.setPrecio(producto.getPrecio());
        super.setExistencias(producto.getExistencias());
        super.setActivo(producto.isActivo());
        super.setRutaImagen(producto.getRutaImagen());
        this.cantidad = 0;
    }
}
