package com.findme.service.controller;

import com.findme.service.dataaccess.MascotaDao;
import com.findme.service.dataaccess.UsuarioDao;
import com.findme.service.gcm.GcmException;
import com.findme.service.gcm.ManejadorGcm;
import com.findme.service.gcm.apimodel.ManejadorApiModel;
import com.findme.service.model.Mascota;
import com.findme.service.model.Usuario;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/notifications")
public class NotificationController {

    private static final Logger LOG = Logger.getLogger(NotificationController.class);
    private static final String SUCCESS_MSG = "Se encontro a %s, se notifica a %s";
    @Autowired
    private MascotaDao mascotaDao;
    @Autowired
    private UsuarioDao usuarioDao;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void notificar(@RequestParam("gcmId") String gcmId) {
        LOG.info("Nueva notificacion ...");
        Usuario usuario = this.usuarioDao.getUsuarioByGcmId(gcmId);

        if (usuario == null) {
            LOG.error("Usuario no registrado");
        }
        else {
            Mascota mascota = usuario.getMascota();

            if (mascota == null) {
                LOG.error("Usuario no tiene mascota regitrada");
            }
            else {
                try {
                    Map<String, String> datos = new HashMap<String, String>();
                    datos.put(ManejadorApiModel.NOMBRE_USUARIO, usuario.getNombre());
                    datos.put(ManejadorApiModel.APELLIDO_USUARIO, usuario.getApellido());
                    datos.put(ManejadorApiModel.CELULAR, usuario.getCelular());
                    datos.put(ManejadorApiModel.CORREO, usuario.getCorreo());
                    datos.put(ManejadorApiModel.REG_ID, usuario.getGcmId());
                    datos.put(ManejadorApiModel.NOMBRE_MASCOTA, mascota.getNombre());

                    ManejadorGcm.instance().enviarNotificacion(datos);
                    LOG.info(String.format(SUCCESS_MSG, mascota.getNombre(), usuario.getGcmId()));
                }
                catch (GcmException ex) {
                    LOG.error(ex.getMessage());
                }
            }
        }
    }
}
