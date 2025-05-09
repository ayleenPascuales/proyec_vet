/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import model.entidades.Mascota;
import java.util.List;
   
public interface MascotaDAO {
    boolean registrarMascota(Mascota mascota);
    List<Mascota> obtenerMascotasPorCliente(String idCliente);
    Mascota obtenerMascotaPorId(String id);
    boolean actualizarMascota(Mascota mascota);
    boolean eliminarMascota(String id);
}

