package controller;

import model.dao.UsuarioDAO;
import model.dao.UsuarioDAOImpl;
import model.dao.VeterinarioDAO;
import model.dao.VeterinarioDAOImpl;
import static model.entidades.Rol.VETERINARIO;
import model.entidades.Usuario;
import model.entidades.Veterinario;

public class VetController {

    private VeterinarioDAO veterinarioDAO;
    private UsuarioDAO usuarioDAO;

    public VetController() {
        this.veterinarioDAO = new VeterinarioDAOImpl();
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public boolean registrarVeterinario(Veterinario vet) {
        boolean registrado = veterinarioDAO.registrarVeterinario(vet);

        if (registrado) {
            Usuario nuevoUsuario = new Usuario(
                vet.getUser(),
                vet.getPassword(),
                VETERINARIO
            );
            usuarioDAO.guardarUsuario(nuevoUsuario);
        }

        return registrado;
    }
}
