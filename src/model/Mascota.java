/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aylee
 */
public class Mascota extends Animal {
   private Dueño dueño;

    public Mascota(Dueño dueño, String nombre, String especie, String sexo, String raza, String edad) {
        super(nombre, especie, sexo, raza, edad);
        this.dueño = dueño;
    }

   
    public Dueño getDueño() {
        return dueño;
    }

    public void setDueño(Dueño dueño) {
        this.dueño = dueño;
    }
   
   
}
