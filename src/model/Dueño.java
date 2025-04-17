/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author aylee
 */
public class Dueño extends Persona{
    private Mascota mascota;

    public Dueño(String numeroDocumento, String nombres, String apellidos, LocalDate fechaNacimiento, String sexo, String email, String telefono) {
        super(numeroDocumento, nombres, apellidos, fechaNacimiento, sexo, email, telefono);
    }
  
    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
    
    
    
}
