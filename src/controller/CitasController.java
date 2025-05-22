/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.dao.CitasDAO;
import model.dao.CitasDAOImpl;
import model.entidades.Citas;
/**
 *
 * @author aylee
 */
public class CitasController {
    
    private CitasDAO citasDAO;

    public CitasController() {
        this.citasDAO = new CitasDAOImpl();
    }

    public void guardarCita(Citas cita) throws Exception {
        if (cita == null) {
            throw new Exception("La cita no puede ser nula");
        }
        
        citasDAO.crear(cita);
    }
}

    
