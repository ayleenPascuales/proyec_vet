/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import java.time.LocalDateTime;
import java.util.List;
import model.entidades.Citas;
/**
 *
 * @author aylee
 */
public interface CitasDAO {
    void crear(Citas cita);
    public Citas buscarPorDocumentoYNombreMascota(String documento, String nombreMascota);
    List<Citas> obtenerTodas();
    void eliminar(String numeroDocumento);
    
}

