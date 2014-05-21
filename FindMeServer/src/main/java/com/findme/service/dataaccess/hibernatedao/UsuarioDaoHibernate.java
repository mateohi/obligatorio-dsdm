package com.findme.service.dataaccess.hibernatedao;

import com.findme.service.dataaccess.UsuarioDao;
import com.findme.service.model.Usuario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDaoHibernate implements UsuarioDao {

    private SessionFactory sessionFactory;

    public UsuarioDaoHibernate() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

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
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(usuario);

        transaction.commit();
        session.close();
    }
}
