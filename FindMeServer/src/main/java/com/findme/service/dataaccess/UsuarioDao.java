package com.findme.service.dataaccess;

import com.findme.service.model.Usuario;
import java.util.List;

public interface UsuarioDao {

    public List<Usuario> getUsuarios();

    public Usuario getUsuarioById(long id);

    public void deleteUsuario(long id);

    public void addUsuario(Usuario usuario);

    public Usuario getUsuarioByGcmId(String gcmId);
}
