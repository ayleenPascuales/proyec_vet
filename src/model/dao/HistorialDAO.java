/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import java.time.LocalDate;
import java.util.List;
import model.entidades.Historial;
/**
 *
 * @author aylee
 */
public interface HistorialDAO {
 
    void crear(Historial historial);
   List<Historial> obtenerPorDocumento(String numeroDocumento);
    List<Historial> obtenerTodos();
    void eliminar(String numeroDocumento);
    //List<Historial> buscarPorNombreMascota(String nombreMascota);
    //List<Historial> buscarPorNombreDueño(String nombreDueño);
    //List<Historial> buscarPorFecha(LocalDate fecha);
}

