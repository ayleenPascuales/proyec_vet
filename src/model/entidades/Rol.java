/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entidades;

/**
 *
 * @author aylee
 */
public enum Rol {
    ADMINISTRADOR("Administrador"),
    CLIENTE("Cliente"),
    VETERINARIO("Veterinario");

    private final String nombre;

    // Constructor para almacenar el nombre bonito
    Rol(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return this.nombre;
    }
}
