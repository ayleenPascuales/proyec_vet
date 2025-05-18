/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entidades;

import java.time.LocalDateTime;

/**
 *
 * @author aylee
 */
public class Citas {
    private String numeroDocumento;
    private String nombreDueno;
    private String apellidoDueno;
    private String telefono;
    private String nombreMascota;
    private String tipoAnimal;
    private int edadMascota;
    private String motivoConsulta;
    private String tipoConsulta;
    private LocalDateTime fechaHoraConsulta;
    private String veterinario;

    public Citas(String numeroDocumento, String nombreDueno, String apellidoDueno, String telefono, String nombreMascota, String tipoAnimal, int edadMascota, String motivoConsulta, String tipoConsulta, LocalDateTime fechaHoraConsulta, String veterinario) {
        this.numeroDocumento = numeroDocumento;
        this.nombreDueno = nombreDueno;
        this.apellidoDueno = apellidoDueno;
        this.telefono = telefono;
        this.nombreMascota = nombreMascota;
        this.tipoAnimal = tipoAnimal;
        this.edadMascota = edadMascota;
        this.motivoConsulta = motivoConsulta;
        this.tipoConsulta = tipoConsulta;
        this.fechaHoraConsulta = fechaHoraConsulta;
        this.veterinario = veterinario;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getApellidoDueno() {
        return apellidoDueno;
    }

    public void setApellidoDueno(String apellidoDueno) {
        this.apellidoDueno = apellidoDueno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public int getEdadMascota() {
        return edadMascota;
    }

    public void setEdadMascota(int edadMascota) {
        this.edadMascota = edadMascota;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public LocalDateTime getFechaHoraConsulta() {
        return fechaHoraConsulta;
    }

    public void setFechaHoraConsulta(LocalDateTime fechaHoraConsulta) {
        this.fechaHoraConsulta = fechaHoraConsulta;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }
    
}
