/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import model.entidades.Usuario;
import model.JsonUtil.JsonUtil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 *
 * @author aylee
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    private static final String ARCHIVO_USUARIOS = "C:\\Users\\aylee\\OneDrive\\Documentos\\NetBeansProjects\\proyecto4\\src\\resources\\data\\usuarios.json";

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
        public List<Usuario> obtenerTodos() {
        return JsonUtil.leerDesdeArchivo(ARCHIVO_USUARIOS, Usuario.class);
    }


    @Override
    public boolean existeUsuario(String username) {
        List<Usuario> usuarios = JsonUtil.leerDesdeArchivo(ARCHIVO_USUARIOS, Usuario.class);
        return usuarios.stream().anyMatch(u -> u.getUser().equals(username));
    }

    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        List<Usuario> usuarios = JsonUtil.leerDesdeArchivo(ARCHIVO_USUARIOS, Usuario.class);
        return usuarios.stream()
                .filter(u -> u.getUser().equals(username))
                .findFirst()
                .orElse(null);
    }
     @Override
    public void guardarUsuario(Usuario usuario) {
        List<Usuario> listaUsuarios = JsonUtil.leerDesdeArchivo(ARCHIVO_USUARIOS, Usuario.class);
        listaUsuarios.add(usuario);
        JsonUtil.guardarEnArchivo(ARCHIVO_USUARIOS, listaUsuarios);
    }
    
    public Map<String, Long> contarUsuariosPorRol() {
    List<Usuario> usuarios = JsonUtil.leerDesdeArchivo(ARCHIVO_USUARIOS, Usuario.class);
    Map<String, Long> conteoPorRol = usuarios.stream()
           .collect(Collectors.groupingBy(u -> u.getRole().name(), Collectors.counting()));
    return conteoPorRol;
}

}
