/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package EjercicioPractico2.EjercicioPractico2.dao;

import EjercicioPractico2.EjercicioPractico2.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDao extends JpaRepository<Venta,Long>{
    
}