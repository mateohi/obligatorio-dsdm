package com.findme.service.dataaccess.dao;

import com.findme.service.dataaccess.IMascotaDao;
import com.findme.service.model.Mascota;
import java.util.List;

public class MascotaDao implements IMascotaDao {

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
