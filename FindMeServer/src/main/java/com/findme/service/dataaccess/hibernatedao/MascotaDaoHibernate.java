package com.findme.service.dataaccess.hibernatedao;

import com.findme.service.dataaccess.MascotaDao;
import com.findme.service.model.Mascota;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class MascotaDaoHibernate implements MascotaDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
