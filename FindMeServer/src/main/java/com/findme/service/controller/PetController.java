package com.findme.service.controller;

import com.findme.service.dataaccess.MascotaDao;
import com.findme.service.dataaccess.UsuarioDao;
import com.findme.service.model.Mascota;
import com.findme.service.model.Usuario;
import com.findme.service.utilities.ManejadorMail;
import com.findme.service.utilities.base64.Base64Exception;
import com.findme.service.utilities.base64.ManejadorBase64;
import com.findme.service.utilities.mail.MailGeneratorException;
import com.findme.service.utilities.qr.QRGeneratorException;
import java.io.IOException;
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
    private static final String PROPS = "project.properties";
    private static final String PNG = "png";
    @Autowired
    private MascotaDao mascotaDao;
    @Autowired
    private UsuarioDao usuarioDao;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void guardarMascota(@RequestBody(required = true) Mascota mascota,
            @RequestParam("gcmId") String gcmId) throws Base64Exception, IOException, QRGeneratorException, MailGeneratorException {
        LOG.info("Nueva mascota");

        Usuario usuario = this.usuarioDao.getUsuarioByGcmId(gcmId);
        if (usuario != null) {
            mascota.setPathFoto(gcmId + mascota.getNombre());
            this.mascotaDao.addMascota(mascota);
            usuario.setMascota(mascota);

            this.usuarioDao.addUsuario(usuario);
            LOG.info("Nueva mascota guardada");

            ManejadorBase64.base64AFile(mascota.getFotoBase64(), mascota.getPathFoto(), PNG);
            LOG.info("Foto guardada");

            ManejadorMail.enviarQR(gcmId + "+" + mascota.getNombre(), usuario.getCorreo(), PROPS);
            LOG.info("Mail enviado");
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{idMascota}/qr")
    public void reenviarQr(@PathVariable("idMascota") long idMascota) {
        LOG.info("Reenviar QR");
        // TODO agregar logica
    }
}
