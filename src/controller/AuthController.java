/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import model.dao.UsuarioDAO;
import model.dao.UsuarioDAOImpl;
import model.entidades.Rol;
import static model.entidades.Rol.CLIENTE;
import model.entidades.Usuario;
/**
 *
 * @author aylee
 */
public class AuthController {
    private static final String USERS_JSON_FILE = "C:\\Users\\aylee\\OneDrive\\Documentos\\NetBeansProjects\\proyecto4\\src\\resources\\data\\usuarios.json";
    private UsuarioDAO usuarioDao;
    private final Gson gson ;
    
    public AuthController() {
        this.usuarioDao = new UsuarioDAOImpl();
        this.gson = new GsonBuilder()
        .setPrettyPrinting() 
        .create();
    }
     
    // Método ComboBox para selección de rol
    public Usuario login(String username, String password, Rol rol) {
        Usuario usuario = usuarioDao.obtenerUsuarioPorUsername(username);
        
        if (usuario != null && 
            usuario.getPassword().equals(password) && 
            usuario.getRole() == rol) {
            return usuario;
        }
        return null;
    }
    
    public boolean registrarUsuario(String username, String password, Rol rol) throws Exception {
        // Verificar si el usuario ya existe
        if (usuarioExiste(username)) {
            return false;
        }
        
        // Obtener todos los usuarios existentes
        List<Usuario> usuarios = obtenerTodosUsuarios();
        
        Usuario nuevoUsuario = new Usuario(username, password, rol);
        // Agregar el nuevo usuario
        usuarios.add(nuevoUsuario);
        
        // Guardar la lista actualizada en el JSON
        guardarUsuariosEnJson(usuarios);
        
        return true;
    }
    private boolean usuarioExiste(String username) {
        List<Usuario> usuarios = obtenerTodosUsuarios();
        return usuarios.stream().anyMatch(u -> u.getUser().equals(username));
    }
    private List<Usuario> obtenerTodosUsuarios() {
        try (FileReader reader = new FileReader(USERS_JSON_FILE)) {
            Type listType = new TypeToken<ArrayList<Usuario>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            return new ArrayList<>(); // Si el archivo no existe, retorna lista vacía
        }
    }
     private void guardarUsuariosEnJson(List<Usuario> usuarios) throws Exception {
        try (FileWriter writer = new FileWriter(USERS_JSON_FILE)) {
                gson.toJson(usuarios, writer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
