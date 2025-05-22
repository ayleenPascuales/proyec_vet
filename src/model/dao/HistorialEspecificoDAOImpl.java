/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import model.dao.HistorialEspecificoDAO;
import model.entidades.HistorialEspecifico;
import model.JsonUtil.JsonUtil;
/**
 *
 * @author aylee
 */
public class HistorialEspecificoDAOImpl implements HistorialEspecificoDAO {
    
    private static final String ARCHIVO_HISTORIAL_ESPECIFICO= "C:\\Users\\aylee\\OneDrive\\Documentos\\NetBeansProjects\\proyecto4\\src\\resources\\data\\historialEspecifico.json";
    
    public HistorialEspecificoDAOImpl() {
        // Inicializar el archivo si no existe
        JsonUtil.inicializarArchivoSiNoExiste(ARCHIVO_HISTORIAL_ESPECIFICO);
    }

    @Override
    public void crear(HistorialEspecifico historial) {
        List<HistorialEspecifico> historiales = obtenerTodos();
        historiales.add(historial);
        JsonUtil.guardarEnArchivo(ARCHIVO_HISTORIAL_ESPECIFICO, historiales);
    }

    @Override
    public List<HistorialEspecifico> buscarPorDocumento(String numeroDocumento) {
        return obtenerTodos().stream()
            .filter(h -> h.getNumeroDocumento().equals(numeroDocumento))
            .collect(Collectors.toList());
    }

    @Override
    public List<HistorialEspecifico> obtenerTodos() {
        return JsonUtil.leerDesdeArchivo(ARCHIVO_HISTORIAL_ESPECIFICO, HistorialEspecifico.class);
    }

    @Override
    public List<HistorialEspecifico> buscarPorNombreMascota(String nombreMascota) {
        return obtenerTodos().stream()
                .filter(h -> h.getNombreMascota().toLowerCase().contains(nombreMascota.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<HistorialEspecifico> buscarPorNombreDueño(String nombreDueño) {
        return obtenerTodos().stream()
                .filter(h -> h.getNombreDueño().toLowerCase().contains(nombreDueño.toLowerCase()))
                .collect(Collectors.toList());
    }

    
}
