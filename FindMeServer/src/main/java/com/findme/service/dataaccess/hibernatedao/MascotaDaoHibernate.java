package com.findme.service.dataaccess.hibernatedao;

import com.findme.service.dataaccess.MascotaDao;
import com.findme.service.model.Mascota;
import java.util.List;

public class MascotaDaoHibernate implements MascotaDao {

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
