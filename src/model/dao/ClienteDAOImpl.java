/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import model.entidades.Cliente;
import model.JsonUtil.JsonUtil;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author aylee
 */
public class ClienteDAOImpl implements ClienteDAO {
    private static final String ARCHIVO_CLIENTES = "C:\\Users\\aylee\\OneDrive\\Documentos\\NetBeansProjects\\proyecto4\\src\\resources\\data\\clientes.json";
    
    @Override
    public boolean guardarCliente(Cliente cliente) {
        try {
            // Validar que el ID no exista ya
            if (obtenerClientePorId(cliente.getNumeroDocumento()) != null) {
                System.err.println("Error: Ya existe un cliente con ID " + cliente.getNumeroDocumento());
                return false;
            }
            
            // Validar que el username no esté ya registrado
            if (obtenerClientePorUsername(cliente.getUser()) != null) {
                System.err.println("Error: El username " + cliente.getUser() + " ya está registrado");
                return false;
            }
            
            List<Cliente> clientes = obtenerTodosClientes();
            clientes.add(cliente);
            return JsonUtil.guardarEnArchivo(ARCHIVO_CLIENTES, clientes);
        } catch (Exception e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) {
        try {
            List<Cliente> clientes = obtenerTodosClientes();
            
            // Buscar y actualizar el cliente
            boolean encontrado = false;
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getNumeroDocumento().equals(cliente.getNumeroDocumento())) {
                    clientes.set(i, cliente);
                    encontrado = true;
                    break;
                }
            }
            
            if (!encontrado) {
                System.err.println("Error: No se encontró el cliente con ID " + cliente.getNumeroDocumento());
                return false;
            }
            
            return JsonUtil.guardarEnArchivo(ARCHIVO_CLIENTES, clientes);
        } catch (Exception e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarCliente(String id) {
        try {
            List<Cliente> clientes = obtenerTodosClientes();
            
            // Filtrar para eliminar el cliente con el ID especificado
            List<Cliente> clientesActualizados = clientes.stream()
                .filter(c -> !c.getNumeroDocumento().equals(id))
                .collect(Collectors.toList());
            
            if (clientes.size() == clientesActualizados.size()) {
                System.err.println("Error: No se encontró el cliente con ID " + id);
                return false;
            }
            
            return JsonUtil.guardarEnArchivo(ARCHIVO_CLIENTES, clientesActualizados);
        } catch (Exception e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Cliente> obtenerTodosClientes() {
        return JsonUtil.leerDesdeArchivo(ARCHIVO_CLIENTES, Cliente.class);
    }

    @Override
    public Cliente obtenerClientePorId(String id) {
        List<Cliente> clientes = obtenerTodosClientes();
        return clientes.stream()
            .filter(c -> c.getNumeroDocumento().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public Cliente obtenerClientePorUsername(String username) {
        List<Cliente> clientes = obtenerTodosClientes();
        return clientes.stream()
            .filter(c -> c.getUser().equals(username))
            .findFirst()
            .orElse(null);
    }
} 

