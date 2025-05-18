/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.dao.MascotaDAO;
import model.dao.MascotaDAOImpl;
import model.entidades.Mascota;
import java.util.List;
/**
 *
 * @author aylee
 */
public class MascotaController {
    private MascotaDAO mascotaDao;
    
    public MascotaController() {
        this.mascotaDao = new MascotaDAOImpl();
    }
    
    /**
     * Registra una nueva mascota en el sistema
     * @param mascota La mascota a registrar
     * @return true si se registró correctamente, false si hubo error
     */
    public boolean registrarMascota(Mascota mascota) {
        // Validación básica
        if (mascota == null || mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            return false;
        }
        
        return mascotaDao.registrarMascota(mascota);
    }
    
    /**
     * Obtiene todas las mascotas de un cliente específico
     * @param idCliente El ID del cliente
     * @return Lista de mascotas del cliente
     */
    public List<Mascota> obtenerMascotasPorCliente(String idCliente) {
        if (idCliente == null || idCliente.trim().isEmpty()) {
            return null;
        }
        return mascotaDao.obtenerMascotasPorCliente(idCliente);
    }
    
    public boolean validarMascota(Mascota mascota) {
        if (mascota == null) return false;
        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) return false;
        if (mascota.getEdad() == null || mascota.getEdad().trim().isEmpty()) return false;
        if (mascota.getEspecie() == null || mascota.getEspecie().trim().isEmpty()) return false;
        if (mascota.getRaza()== null || mascota.getRaza().trim().isEmpty()) return false;
        if (mascota.getSexo()== null || mascota.getSexo().trim().isEmpty()) return false;
        //if (mascota.isEstetilizado()== null || mascota.isEstetilizado().trim().isEmpty()) return false;
        if (mascota.getPeso() == null || mascota.getPeso().trim().isEmpty()) return false;
        return true;
    }
}

