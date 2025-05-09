/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import model.entidades.Usuario;
import model.JsonUtil.JsonUtil;
import java.util.List;
/**
 *
 * @author aylee
 */
public class UsuarioDAOImpl implements UsuarioDAO {
    private static final String ARCHIVO_USUARIOS = "usuarios.json";

    @Override
    public boolean registrarUsuario(Usuario usuario) {
        List<Usuario> usuarios = JsonUtil.leerDesdeArchivo(ARCHIVO_USUARIOS, Usuario.class);
        usuarios.add(usuario);
        return JsonUtil.guardarEnArchivo(ARCHIVO_USUARIOS, usuarios);
    }

    @Override
    public Usuario autenticar(String username, String password) {
        List<Usuario> usuarios = JsonUtil.leerDesdeArchivo(ARCHIVO_USUARIOS, Usuario.class);
        return usuarios.stream()
                .filter(u -> u.getUser().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existeUsuario(String username) {
        List<Usuario> usuarios = JsonUtil.leerDesdeArchivo(ARCHIVO_USUARIOS, Usuario.class);
        return usuarios.stream().anyMatch(u -> u.getUser().equals(username));
    }

    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
