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
public class Historial {
    private String numeroDocumento;
    private String nombreDueño;
    private String tipo;
    private int edad;
    private String motivoConsulta;
    private LocalDate fechaConsulta;
    private String nombreMascota;
    private String veterinario;
    
    public Historial(){
        
    }

    public Historial(String numeroDocumento, String nombreDueño, String tipo, int edad, String motivoConsulta, LocalDate fechaConsulta, String nombreMascota, String veterinario) {
        this.numeroDocumento = numeroDocumento;
        this.nombreDueño = nombreDueño;
        this.tipo = tipo;
        this.edad = edad;
        this.motivoConsulta = motivoConsulta;
        this.fechaConsulta = fechaConsulta;
        this.nombreMascota = nombreMascota;
        this.veterinario = veterinario;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreDueño() {
        return nombreDueño;
    }

    public void setNombreDueño(String nombreDueño) {
        this.nombreDueño = nombreDueño;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public LocalDate getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDate fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    
}
