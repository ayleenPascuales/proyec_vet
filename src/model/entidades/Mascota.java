
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entidades;

import model.entidades.Cliente;
import model.entidades.Animal;

/**
 *
 * @author aylee
 */
public class Mascota extends Animal {
   private Cliente dueño;
   private String idCliente;

    public Mascota(Cliente dueño, String idCliente, String nombre, String edad, String especie, String raza, String sexo, boolean estetilizado, String peso) {
        super(nombre, edad, especie, raza, sexo, estetilizado, peso);
        this.dueño = dueño;
        this.idCliente = idCliente;
    }

    public Cliente getDueño() {
        return dueño;
    }

    public void setDueño(Cliente dueño) {
        this.dueño = dueño;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    

    
   
   
}
