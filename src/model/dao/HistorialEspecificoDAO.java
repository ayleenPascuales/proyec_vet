/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import java.time.LocalDate;
import java.util.List;
import model.entidades.HistorialEspecifico;
/**
 *
 * @author aylee
 */
public interface HistorialEspecificoDAO {
    void crear(HistorialEspecifico historial);
    List<HistorialEspecifico> buscarPorDocumento(String numeroDocumento);
    List<HistorialEspecifico> obtenerTodos();
    List<HistorialEspecifico> buscarPorNombreMascota(String nombreMascota);
    List<HistorialEspecifico> buscarPorNombreDueño(String nombreDueño);
    List<HistorialEspecifico> buscarPorDocumentoYFecha(String numeroDocumento, LocalDate fechaConsulta);
}

