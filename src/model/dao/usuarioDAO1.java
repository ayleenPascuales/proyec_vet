/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
/*
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import model.entidades.Rol;
import model.entidades.Usuario;

/**
 *
 * @author aylee


public class usuarioDAO1 {
    private static final String JSON_FILE = "C:\\Users\\aylee\\OneDrive\\Documentos\\NetBeansProjects\\proyecto4\\src\\resources\\data\\usuarios.json";
    
    //validar crendenciales
    public Usuario validarCredenciales(String user, String password) {
        try (FileReader reader = new FileReader(JSON_FILE)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray usuariosJson = jsonObject.getAsJsonArray("usuarios");

            for (int i = 0; i < usuariosJson.size(); i++) {
                JsonObject usuarioJson = usuariosJson.get(i).getAsJsonObject();

                String userUser = usuarioJson.get("user").getAsString();
                String userPassword = usuarioJson.get("password").getAsString();

                if (userUser.equals(user) && userPassword.equals(password)) {
                    Rol rol = Rol.valueOf(usuarioJson.get("rol").getAsString());
                    return new Usuario(userUser, userPassword, rol, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean esAdministrador(Usuario usuario) {
        return usuario != null && usuario.getRole() == Rol.ADMINISTRADOR;
    }
    public boolean esVeterinario(Usuario veterinario){
        return veterinario !=null && veterinario.getRole()==Rol.VETERIANARIO;
    }
    public boolean esCliente(Usuario cliente){
        return  cliente !=null &&  cliente.getRole()==Rol.CLIENTE;
    }
    
}*/
