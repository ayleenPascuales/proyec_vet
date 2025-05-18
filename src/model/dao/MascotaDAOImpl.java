/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import model.entidades.Mascota;
import model.JsonUtil.JsonUtil;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author aylee
 */
public class MascotaDAOImpl implements MascotaDAO {
    private static final String ARCHIVO_MASCOTAS = "C:\\Users\\aylee\\OneDrive\\Documentos\\NetBeansProjects\\proyecto4\\src\\resources\\data\\mascotas.json";
    
    @Override
    public boolean registrarMascota(Mascota mascota) {
        List<Mascota> mascotas = JsonUtil.leerDesdeArchivo(ARCHIVO_MASCOTAS, Mascota.class);
        mascotas.add(mascota);
        return JsonUtil.guardarEnArchivo(ARCHIVO_MASCOTAS, mascotas);
    }

    @Override
    public List<Mascota> obtenerMascotasPorCliente(String idCliente) {
        List<Mascota> mascotas = JsonUtil.leerDesdeArchivo(ARCHIVO_MASCOTAS, Mascota.class);
        return mascotas.stream()
                .filter(m -> m.getIdCliente().equals(idCliente))
                .collect(Collectors.toList());
    }
}
