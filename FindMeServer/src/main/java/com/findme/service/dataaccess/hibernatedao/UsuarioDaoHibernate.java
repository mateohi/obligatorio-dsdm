package com.findme.service.dataaccess.hibernatedao;

import com.findme.service.dataaccess.UsuarioDao;
import com.findme.service.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class UsuarioDaoHibernate implements UsuarioDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

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
