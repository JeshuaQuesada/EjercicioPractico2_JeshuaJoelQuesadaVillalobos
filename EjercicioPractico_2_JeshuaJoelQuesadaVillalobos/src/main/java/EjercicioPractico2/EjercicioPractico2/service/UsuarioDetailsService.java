/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package EjercicioPractico2.EjercicioPractico2.service;


import EjercicioPractico2.EjercicioPractico2.domain.Usuario;
import java.util.List;
import org.springframework.security.core.userdetails.*;

public interface UsuarioDetailsService {
    
    // Se obtiene un listado de usuarios en un List
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException;
    
}