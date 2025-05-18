/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import model.dao.HistorialDAO;
import model.entidades.Historial;
import model.JsonUtil.JsonUtil;

public class HistorialDAOImpl implements HistorialDAO {
    
    private static final String ARCHIVO_HISTORIAL = "C:\\Users\\aylee\\OneDrive\\Documentos\\NetBeansProjects\\proyecto4\\src\\resources\\data\\historiales.json";
    
    public HistorialDAOImpl() {
        // Inicializar el archivo si no existe
        JsonUtil.inicializarArchivoSiNoExiste(ARCHIVO_HISTORIAL);
    }

    @Override
    public void crear(Historial historial) {
        List<Historial> historiales = obtenerTodos();
        historiales.add(historial);
        JsonUtil.guardarEnArchivo(ARCHIVO_HISTORIAL, historiales);
    }

    @Override
    public Historial buscarPorId(String numeroDocumento) {
        return obtenerTodos().stream()
                .filter(h -> h.getNumeroDocumento().equals(numeroDocumento))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Historial> obtenerTodos() {
        return JsonUtil.leerDesdeArchivo(ARCHIVO_HISTORIAL, Historial.class);
    }

    
    @Override
    public void eliminar(String numeroDocumento) {
        List<Historial> historiales = obtenerTodos().stream()
                .filter(h -> !h.getNumeroDocumento().equals(numeroDocumento))
                .collect(Collectors.toList());
        JsonUtil.guardarEnArchivo(ARCHIVO_HISTORIAL, historiales);
    }
    /*
    @Override
    public List<Historial> buscarPorNombreMascota(String nombreMascota) {
        return obtenerTodos().stream()
                .filter(h -> h.getNombreMascota().toLowerCase().contains(nombreMascota.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Historial> buscarPorNombreDueño(String nombreDueño) {
        return obtenerTodos().stream()
                .filter(h -> h.getNombreDueño().toLowerCase().contains(nombreDueño.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Historial> buscarPorFecha(LocalDate fecha) {
        return obtenerTodos().stream()
                .filter(h -> h.getFechaConsulta().equals(fecha))
                .collect(Collectors.toList());
    }*/
}