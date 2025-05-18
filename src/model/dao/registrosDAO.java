/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import model.entidades.Registros;
import java.util.List;


/**
 *
 * @author aylee
 */
public interface RegistrosDAO {

    void crear(Registros registro) throws IllegalArgumentException;

    boolean existeUsuario(String usuario);

    boolean existeEmail(String email);

    List<Registros> obtenerTodos();
}

