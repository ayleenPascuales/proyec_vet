/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.dao.HistorialEspecificoDAO;
import model.dao.HistorialEspecificoDAOImpl;
import model.entidades.HistorialEspecifico;

/**
 *
 * @author aylee
 */
public class HistorialEspecificoController {

    private HistorialEspecificoDAO historialDAO;

    public HistorialEspecificoController() {
        this.historialDAO = new HistorialEspecificoDAOImpl();
    }

    public void guardarHistorial(HistorialEspecifico historial) {
        if (historial.getNombreDueño() == null || historial.getNombreDueño().isEmpty()) {
            throw new IllegalArgumentException("El nombre del dueño no puede estar vacío");
        }


        historialDAO.crear(historial);
    }

}
