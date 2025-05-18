/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import model.dao.RegistrosDAO;
import model.entidades.Registros;
import model.JsonUtil.JsonUtil;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author aylee
 */
public class RegistrosDAOImpl implements RegistrosDAO {
    
    private static final String ARCHIVO_REGISTROS = "data/registros.json";
    
    public RegistrosDAOImpl() {
        JsonUtil.inicializarArchivoSiNoExiste(ARCHIVO_REGISTROS);
    }

    @Override
    public void crear(Registros registro) throws IllegalArgumentException {
        
        // Verificar unicidad de usuario y email
        if (existeUsuario(registro.getUsuario())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }
        
        if (existeEmail(registro.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        
        List<Registros> registros = obtenerTodos();
        registros.add(registro);
        JsonUtil.guardarEnArchivo(ARCHIVO_REGISTROS, registros);
    }

    @Override
    public boolean existeUsuario(String usuario) {
        return obtenerTodos().stream()
                .anyMatch(r -> r.getUsuario().equalsIgnoreCase(usuario));
    }

    @Override
    public boolean existeEmail(String email) {
        return obtenerTodos().stream()
                .anyMatch(r -> r.getEmail().equalsIgnoreCase(email));
    }

   @Override
    public List<Registros> obtenerTodos() {
        return JsonUtil.leerDesdeArchivo(ARCHIVO_REGISTROS, Registros.class);
    }
}
