/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entidades;

import model.entidades.Rol;
import model.entidades.Persona;
import model.entidades.Mascota;
import model.entidades.Historial;
import model.entidades.Citas;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aylee
 */
public class Veterinario extends Persona{
    
    private String user;
    private String password;
    private String cargo;
    private String experiencia;
    private List<Mascota> mascotas = new ArrayList<>();
    private List<Citas> citas = new ArrayList<>();
    private List<Historial> historial = new ArrayList<>();

    public Veterinario(String numeroDocumento, String nombres, String apellidos, String email, String telefono, String cargo, String experiencia, LocalDate fechaNacimiento) {
    super(numeroDocumento, nombres, apellidos, fechaNacimiento, email, telefono);
    this.cargo = cargo;
    this.experiencia = experiencia;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
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
