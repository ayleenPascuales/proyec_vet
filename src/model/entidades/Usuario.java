package model.entidades;

import model.entidades.Rol;
import model.entidades.Persona;

public class Usuario {
    private String user;
    private String password;
    private Rol role;
    //private Persona persona; 

    public Usuario(String user, String password, Rol role) {
        this.user = user;
        this.password = password;
        this.role = role;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }   
}