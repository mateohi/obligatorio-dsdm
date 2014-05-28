package com.findme.service.controller;

import com.findme.service.dataaccess.MascotaDao;
import com.findme.service.model.Mascota;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/pets")
public class PetController {

    private static final Logger LOG = Logger.getLogger(PetController.class);
    @Autowired
    private MascotaDao mascotaDao;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void guardarMascota(@RequestBody(required = true) Mascota mascota,
            @RequestParam("idDueno") long idDueno) {
        LOG.info("Nueva mascota");
        this.mascotaDao.addMascota(mascota);
        // TODO asignar algo a su dueno
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{idMascota}/qr")
    public void reenviarQr(@PathVariable("idMascota") long idMascota) {
        LOG.info("Reenviar QR");
        // TODO agregar logica
    }
}
