package com.findme.service.dataaccess;

import com.findme.service.model.Mascota;
import java.util.List;

public interface MascotaDao {

    public List<Mascota> getMascotas();

    public Mascota getMascotaById(long id);

    public void deleteMascota(long id);

    public void addMascota(Mascota mascota);
}
