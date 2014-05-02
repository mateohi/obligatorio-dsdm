package com.findme.service.dataaccess.dao;

import com.findme.service.dataaccess.IUsuarioDao;
import com.findme.service.model.Usuario;
import java.util.List;

public class UsuarioDao implements IUsuarioDao {

    @Override
    public List<Usuario> getUsuarios() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Usuario getUsuarioById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteUsuario(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
