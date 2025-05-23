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
public class HistorialEspecifico extends Historial {
    private String apellido;
    private String telefono;
    private String generoMascota;
    private String diagnostico;
    
    
    public HistorialEspecifico() {
        super(); 
    }
    
    public HistorialEspecifico(String nroDocumento,String nombreDueño, String apellido, String telefono, String veterinario,
            LocalDate fechaConsulta, String nombreMascota, String generoMascota, String tipo, 
             int edad, String diagnostico , String motivoConsulta) {
        super(nroDocumento,nombreDueño, tipo, edad, motivoConsulta, fechaConsulta, nombreMascota, veterinario);
        this.apellido = apellido;
        this.telefono = telefono;
        this.generoMascota = generoMascota;
        this.diagnostico = diagnostico;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGeneroMascota() {
        return generoMascota;
    }

    public void setGeneroMascota(String generoMascota) {
        this.generoMascota = generoMascota;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }  
}
