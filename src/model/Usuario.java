package model;

public class Usuario {
    private String user;
    private String password;
    private Rol role;
    private Persona persona; 

    public Usuario(String user, String password, Rol role, Persona persona) {
        this.user = user;
        this.password = password;
        this.role = role;
        this.persona = persona;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String email) {
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    

}