/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import model.dao.CitasDAO;
import model.entidades.Citas;
import model.JsonUtil.JsonUtil;
/**
 *
 * @author aylee
 */
public class CitasDAOImpl implements CitasDAO { 
    private static final String ARCHIVO_CITAS = "C:\\Users\\aylee\\OneDrive\\Documentos\\NetBeansProjects\\proyecto4\\src\\resources\\data\\citas.json";
    public CitasDAOImpl() {
        // Inicializar el archivo si no existe
        JsonUtil.inicializarArchivoSiNoExiste(ARCHIVO_CITAS);
    }

    @Override
    public void crear(Citas cita) {
        List<Citas> citas = obtenerTodas();
        citas.add(cita);
        JsonUtil.guardarEnArchivo(ARCHIVO_CITAS, citas);
    }

    @Override
    public Citas buscarPorId(String numeroDocumento) {
        return obtenerTodas().stream()
                .filter(c -> c.getNumeroDocumento().equals(numeroDocumento))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Citas> obtenerTodas() {
        return JsonUtil.leerDesdeArchivo(ARCHIVO_CITAS, Citas.class);
    }
    @Override
    public void eliminar(String numeroDocumento) {
        List<Citas> citas = obtenerTodas().stream()
                .filter(c -> !c.getNumeroDocumento().equals(numeroDocumento))
                .collect(Collectors.toList());
        JsonUtil.guardarEnArchivo(ARCHIVO_CITAS, citas);
    }

    @Override
    public List<Citas> buscarPorNombreMascota(String nombreMascota) {
        return obtenerTodas().stream()
                .filter(c -> c.getNombreMascota().toLowerCase().contains(nombreMascota.toLowerCase()))
                .collect(Collectors.toList());
    }
}

