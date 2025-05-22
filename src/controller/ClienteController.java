/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.time.LocalDate;
import model.dao.ClienteDAO;
import model.dao.ClienteDAOImpl;
import model.entidades.Cliente;
/**
 *
 * @author aylee
 */
public class ClienteController {
    private ClienteDAO clienteDao;
    
    public ClienteController() {
        this.clienteDao = new ClienteDAOImpl();
    }
    
    public boolean registrarCliente(String user, String numeroDocumento, String nombres, String apellidos, LocalDate fechaNacimiento, String email, String telefono) {
        Cliente nuevoCliente = new Cliente(user, numeroDocumento, nombres, apellidos, fechaNacimiento,email, telefono);
        return clienteDao.guardarCliente(nuevoCliente);
    }
}
