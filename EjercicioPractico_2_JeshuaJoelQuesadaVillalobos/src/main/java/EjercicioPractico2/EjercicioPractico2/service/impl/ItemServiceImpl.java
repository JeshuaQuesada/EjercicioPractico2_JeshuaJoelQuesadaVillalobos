/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioPractico2.EjercicioPractico2.service.impl;

import EjercicioPractico2.EjercicioPractico2.dao.*;
import EjercicioPractico2.EjercicioPractico2.domain.*;
import EjercicioPractico2.EjercicioPractico2.service.*;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private HttpSession session;

    @Override
    public List<Item> gets() {
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        return listaItems;
    }

    @Override
    public Item get(Item item) {
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            for (Item i : listaItems) {
                if (i.getIdProducto() == item.getIdProducto()) {
                    return i;
                }
            }
        }
        return null;
    }

    @Override
    public void delete(Item item) {
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems == null) {
            listaItems = new ArrayList<>();
        }
        var existe = false;
        for (Item i : listaItems) {
            if (i.getIdProducto() == item.getIdProducto()) {
                existe = true;
                if (i.getCantidad() < i.getExistencias()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;
            }
        }
        if (!existe) {
            item.setCantidad(1);
            listaItems.add(item);
        }
        session.setAttribute("listaItems", listaItems);
    }

    @Override
    public void save(Item item) {
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems == null) {
            listaItems = new ArrayList<>();
        }
        var existe = false;
        for (Item i : listaItems) {
            if (i.getIdProducto() == item.getIdProducto()) {
                existe = true;
                if (i.getCantidad() < i.getExistencias()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;
            }
        }
        if (!existe) {
            item.setCantidad(1);
            listaItems.add(item);
        }
        session.setAttribute("listaItems", listaItems);
    }

    @Override
    public void update(Item item) {
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            for (Item i : listaItems) {
                if (i.getIdProducto() == item.getIdProducto()) {
                    i.setCantidad(item.getCantidad());
                    session.setAttribute("listaItems", listaItems);
                    break;
                }
            }
        }
    }

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private ProductoDao productoDao;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;

    @Override
    public void facturar() {
        // Se debe recuperar el usuario autenticado y obtener su idUsuario
        String username;
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            System.out.println("username en blanco...");
            return;
        }

        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            System.out.println("Usuario no existe en usuarios...");
            return;
        }

// Se debe registrar la factura incluyendo el usuario
        Factura factura = new Factura(usuario.getIdUsuario());
        factura = facturaDao.save(factura);

// Se debe registrar las ventas de cada producto -actualizando existencias-
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            double total = 0;
            for (Item i : listaItems) {
                Venta venta = new Venta(factura.getIdFactura(),
                        i.getIdProducto(),
                        i.getPrecio(),
                        i.getCantidad());
                ventaDao.save(venta);
                Producto producto = productoDao.getReferenceById(i.getIdProducto());
                producto.setExistencias(producto.getExistencias() - i.getCantidad());
                productoDao.save(producto);
                total += i.getCantidad() * i.getPrecio();
            }
            // Se debe registrar el total de la venta en la factura
            factura.setTotal(total);
            facturaDao.save(factura);
            // Se debe limpiar el carrito la lista...
            listaItems.clear();
        }

    }

    public double getTotal() {
        // Se debe registrar las ventas de cada producto -actualizando existencias-
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        double total = 0;
        if (listaItems != null) {
            for (Item i : listaItems) {
                total += i.getCantidad() * i.getPrecio();
            }
        }
        return total;
    }
}
