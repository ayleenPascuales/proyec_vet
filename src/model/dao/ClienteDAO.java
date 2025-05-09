/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import model.entidades.Cliente;
import java.util.List;
/**
 *
 * @author aylee
 */
public interface ClienteDAO {
    
    boolean guardarCliente(Cliente cliente);
    boolean actualizarCliente(Cliente cliente);
    boolean eliminarCliente(String documento);
    List<Cliente> obtenerTodosClientes();
    Cliente obtenerClientePorId(String documento);
    Cliente obtenerClientePorUsername(String user);
}
