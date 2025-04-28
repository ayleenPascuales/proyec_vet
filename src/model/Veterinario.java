/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author aylee
 */
public class Veterinario extends Persona{
    
    String codigoVeterinario;
    private List<Mascota> mascotas;
    private List<Citas> citas;
    private List<Historial> historial;

    public Veterinario(String codigoVeterinario, String numeroDocumento, String nombres, String apellidos, LocalDate fechaNacimiento, String sexo, String email, String telefono) {
        super(numeroDocumento, nombres, apellidos, fechaNacimiento, sexo, email, telefono);
        this.codigoVeterinario = codigoVeterinario;
    }

    public Veterinario(String codigoVeterinario, Rol role) {
        super(role);
        this.codigoVeterinario = codigoVeterinario;
    }

    public String getCodigoVeterinario() {
        return codigoVeterinario;
    }

    public void setCodigoVeterinario(String codigoVeterinario) {
        this.codigoVeterinario = codigoVeterinario;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public List<Citas> getCitas() {
        return citas;
    }

    public void setCitas(List<Citas> citas) {
        this.citas = citas;
    }

    public List<Historial> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Historial> historial) {
        this.historial = historial;
    }
    

   
    
}
