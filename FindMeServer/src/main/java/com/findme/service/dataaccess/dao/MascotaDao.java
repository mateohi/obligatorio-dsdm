package com.findme.service.dataaccess.dao;

import com.findme.service.dataaccess.IMascotaDao;
import com.findme.service.model.Mascota;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MascotaDao implements IMascotaDao {

    @Override
    public List<Mascota> getMascotas() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory sfr = cfg.buildSessionFactory(serviceRegistry);
        Session session = sfr.openSession();
        Transaction tx = session.beginTransaction();

        session.update(null);

        tx.commit();
        session.close();
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
