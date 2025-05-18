/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import model.entidades.Veterinario;
import model.JsonUtil.JsonUtil;
import java.util.List;
import java.util.stream.Collectors;
import model.entidades.Usuario;

public class VeterinarioDAOImpl implements VeterinarioDAO {
    private static final String ARCHIVO_VETERINARIOS = "veterinarios.json";
    
    @Override
    public boolean registrarVeterinario(Veterinario veterinario) {
        try {
            // Validar que no exista un veterinario con el mismo documento o usuario
            if (existeVeterinarioConDocumento(veterinario.getNumeroDocumento())) {
                System.err.println("Error: Ya existe un veterinario con documento " + veterinario.getNumeroDocumento());
                return false;
            }
            
            List<Veterinario> veterinarios = obtenerTodosVeterinarios();
            veterinarios.add(veterinario);
            return JsonUtil.guardarEnArchivo(ARCHIVO_VETERINARIOS, veterinarios);
        } catch (Exception e) {
            System.err.println("Error al registrar veterinario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarVeterinario(Veterinario veterinario) {
        try {
            List<Veterinario> veterinarios = obtenerTodosVeterinarios();
            
            // Buscar y actualizar el veterinario
            boolean encontrado = false;
            for (int i = 0; i < veterinarios.size(); i++) {
                if (veterinarios.get(i).getNumeroDocumento().equals(veterinario.getNumeroDocumento())) {
                    veterinarios.set(i, veterinario);
                    encontrado = true;
                    break;
                }
            }
            
            if (!encontrado) {
                System.err.println("Error: No se encontró el veterinario con documento " + veterinario.getNumeroDocumento());
                return false;
            }
            
            return JsonUtil.guardarEnArchivo(ARCHIVO_VETERINARIOS, veterinarios);
        } catch (Exception e) {
            System.err.println("Error al actualizar veterinario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarVeterinario(String numeroDocumento) {
        try {
            List<Veterinario> veterinarios = obtenerTodosVeterinarios();
            
            // Filtrar para eliminar el veterinario con el código especificado
            List<Veterinario> veterinariosActualizados = veterinarios.stream()
                .filter(v -> !v.getNumeroDocumento().equals(numeroDocumento))
                .collect(Collectors.toList());
            
            if (veterinarios.size() == veterinariosActualizados.size()) {
                System.err.println("Error: No se encontró el veterinario con este documento " + numeroDocumento);
                return false;
            }
            
            return JsonUtil.guardarEnArchivo(ARCHIVO_VETERINARIOS, veterinariosActualizados);
        } catch (Exception e) {
            System.err.println("Error al eliminar veterinario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Veterinario> obtenerTodosVeterinarios() {
        return JsonUtil.leerDesdeArchivo(ARCHIVO_VETERINARIOS, Veterinario.class);
    }

    @Override
    public Veterinario obtenerVeterinarioPorDocumento(String numeroDocumento) {
        List<Veterinario> veterinarios = obtenerTodosVeterinarios();
        return veterinarios.stream()
            .filter(v -> v.getNumeroDocumento().equals(numeroDocumento))
            .findFirst()
            .orElse(null);
    }

    @Override
    public boolean existeVeterinarioConDocumento(String numeroDocumento) {
        List<Veterinario> veterinarios = obtenerTodosVeterinarios();
        return veterinarios.stream()
            .anyMatch(v -> v.getNumeroDocumento().equals(numeroDocumento));
    }
    
    @Override
    public boolean existeVeterinarioConUsuario(String usuario) {
        return obtenerTodosVeterinarios().stream()
                .anyMatch(v -> v.getUser() != null && v.getUser().equals(usuario));
    }
}

