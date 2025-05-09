/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;


import model.entidades.Usuario;
import java.util.List;
import model.entidades.Usuario;

public interface UsuarioDAO {
    boolean registrarUsuario(Usuario usuario);
    Usuario autenticar(String username, String password);
    boolean existeUsuario(String username);

    public Usuario obtenerUsuarioPorUsername(String username);
}