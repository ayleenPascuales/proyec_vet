/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entidades;

import java.time.LocalDate;

/**
 *
 * @author aylee
 */
public class Cliente extends Persona{
    private String user;

    public Cliente(String user, String numeroDocumento, String nombres, String apellidos, LocalDate fechaNacimiento, String email, String telefono) {
        super(numeroDocumento, nombres, apellidos, fechaNacimiento, email, telefono);
        this.user = user;
    }

    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    
    
    

    
    
}
