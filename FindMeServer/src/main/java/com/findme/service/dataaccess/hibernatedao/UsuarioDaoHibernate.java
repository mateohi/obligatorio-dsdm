package com.findme.service.dataaccess.hibernatedao;

import com.findme.service.dataaccess.UsuarioDao;
import com.findme.service.model.Usuario;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UsuarioDaoHibernate implements UsuarioDao {
	
	private SessionFactory sessionFactory;
	
	public UsuarioDaoHibernate() {
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}
	
    @Override
    public List<Usuario> getUsuarios() {
    	Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Usuario> usuarios = session.createCriteria(Usuario.class).list();

        transaction.commit();
        session.close();

        return usuarios;
    }

    @Override
    public Usuario getUsuarioById(long id) {
    	Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Usuario usuario = (Usuario) session.get(Usuario.class, id);

        transaction.commit();
        session.close();

        return usuario;
    }

    @Override
    public void deleteUsuario(long id) {
    	Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Usuario usuario = (Usuario) session.get(Usuario.class, id);
        session.delete(usuario);

        transaction.commit();
        session.close();
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
