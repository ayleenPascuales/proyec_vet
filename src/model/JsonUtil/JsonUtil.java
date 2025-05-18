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
import static java.lang.Math.log;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.JsonUtil.JsonLocalDateAdapter;
/**
 *
 * @author aylee
 */
public class JsonUtil {
   
    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new JsonLocalDateAdapter())
        .registerTypeAdapter(LocalDateTime.class, new JsonLocalTimeAdapter())
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

    if (!archivo.exists()) {
        return new ArrayList<>();
    }
    if (archivo.length() == 0) {
        return new ArrayList<>();
    }
    try (Reader reader = new FileReader(archivo)) {
        Type tipoLista = TypeToken.getParameterized(List.class, clase).getType();
        List<T> lista = gson.fromJson(reader, tipoLista);
        return lista != null ? lista : new ArrayList<>();
    } catch (JsonSyntaxException e) {
        System.err.println("El archivo JSON está mal formado: " + nombreArchivo);
        e.printStackTrace();
        return new ArrayList<>();
    } catch (IOException e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}
    

}

