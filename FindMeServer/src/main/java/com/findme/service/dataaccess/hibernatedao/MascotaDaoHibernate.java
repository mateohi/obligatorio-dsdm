package com.findme.service.dataaccess.hibernatedao;

import com.findme.service.dataaccess.MascotaDao;
import com.findme.service.model.Mascota;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
public class MascotaDaoHibernate implements MascotaDao {

    private SessionFactory sessionFactory;

    public MascotaDaoHibernate() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Mascota> getMascotas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mascota getMascotaById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteMascota(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addMascota(Mascota mascota) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(mascota);

        transaction.commit();
        session.close();
    }
}
