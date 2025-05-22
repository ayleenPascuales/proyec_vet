/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import model.entidades.Veterinario;
import java.util.List;

/**
 *
 * @author aylee
 */
public interface VeterinarioDAO {
    
    boolean registrarVeterinario(Veterinario veterinario);
    
    boolean actualizarVeterinario(Veterinario veterinario);
    
    boolean eliminarVeterinario(String nroDcoumento);
    
    List<Veterinario> obtenerTodosVeterinarios();
    
    Veterinario obtenerVeterinarioPorDocumento(String numeroDocumento);
    
    boolean existeVeterinarioConDocumento(String numeroDocumento);
    
    boolean existeVeterinarioConUsuario(String usuario);
}

