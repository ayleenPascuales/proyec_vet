/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.JsonUtil.JsonLocalDateAdapter;
import model.JsonUtil.JsonLocalTimeAdapter;
/**
 *
 * @author aylee
 */
public class JsonUtil {
   
    private static final Gson gson = new GsonBuilder()
    .registerTypeAdapter(LocalDate.class, new JsonLocalDateAdapter())
    .registerTypeAdapter(LocalTime.class, new JsonLocalTimeAdapter())         // ← Nuevo
    .setPrettyPrinting()
    .create();

    
    public static <T> boolean guardarEnArchivo(String nombreArchivo, List<T> datos) {
        try (Writer writer = new FileWriter(nombreArchivo)) {
            gson.toJson(datos, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void inicializarArchivoSiNoExiste(String nombreArchivo) {
    File archivo = new File(nombreArchivo);
    if (!archivo.exists()) {
        try (Writer writer = new FileWriter(archivo)) {
            writer.write("[]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    public static <T> List<T> leerDesdeArchivo(String nombreArchivo, Class<T> clase) {
    File archivo = new File(nombreArchivo);

    if (!archivo.exists() || archivo.length() == 0) {
        return new ArrayList<>();
    }
    try (Reader reader = new FileReader(archivo)) {
        Type tipoLista = TypeToken.getParameterized(List.class, clase).getType();
        List<T> lista = gson.fromJson(reader, tipoLista);
        System.out.println("Lista cargada de archivo (" + nombreArchivo + "): " + (lista != null ? lista.size() : 0));
        return lista != null ? lista : new ArrayList<>();
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}

}

