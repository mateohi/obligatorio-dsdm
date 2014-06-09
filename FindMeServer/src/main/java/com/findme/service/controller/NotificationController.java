package com.findme.service.controller;

import com.findme.service.dataaccess.NotificationDao;
import com.findme.service.dataaccess.UsuarioDao;
import com.findme.service.gcm.GcmException;
import com.findme.service.gcm.ManejadorGcm;
import com.findme.service.gcm.apimodel.Locacion;
import com.findme.service.gcm.apimodel.ManejadorApiModel;
import com.findme.service.model.Mascota;
import com.findme.service.model.Notification;
import com.findme.service.model.Usuario;
import com.findme.service.utilities.base64.Base64Exception;
import com.findme.service.utilities.base64.ManejadorBase64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/notifications")
public class NotificationController {

    private static final Logger LOG = Logger.getLogger(NotificationController.class);
    private static final String PNG = "png";
    private static final String SUCCESS_MSG = "Se encontro a %s, se notifica a %s";
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private NotificationDao notificationDao;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Notification notificar(@RequestBody(required = true) Locacion locacion, @RequestParam("gcmId") String gcmId, @RequestParam("gcmIdDueno") String gcmIdDueno) {
        LOG.info("Nueva notificacion ...");
        Usuario denunciante = this.usuarioDao.getUsuarioByGcmId(gcmId);
        Usuario dueno = this.usuarioDao.getUsuarioByGcmId(gcmIdDueno);

        if (dueno == null || denunciante == null) {
            LOG.error("Usuario no registrado");
        }
        else {
            Mascota mascota = dueno.getMascota();

            if (mascota == null) {
                LOG.error("Usuario no tiene mascota regitrada");
            }
            else {
                try {
                    Notification notification = new Notification();
                    notification.setLatitud(locacion.getLatitud());
                    notification.setLongitud(locacion.getLongitud());
                    notification.setNombreUsuario(dueno.getNombre());
                    notification.setApellidoUsuario(dueno.getApellido());
                    notification.setCelular(dueno.getCelular());
                    notification.setCorreo(dueno.getCorreo());
                    notification.setNombreMascota(mascota.getNombre());
                    notification.setFecha(new Date());
                    notification.setEstaVacunada(mascota.tenerCuidado());
                    notification.setTenerCuidado(mascota.estaVacunada());
                    notification.setGcmIdDenunciante(gcmId);
                    notification.setGcmIdDueno(gcmIdDueno);
                    this.notificationDao.addNotification(notification);
                    LOG.info("Notification guardada ...");
                    String fotoBase64 = ManejadorBase64.fileABase64(mascota.getPathFoto(), PNG);
                    notification.setFotoBase64(fotoBase64);

                    Map<String, String> datos = new HashMap<String, String>();
                    datos.put(ManejadorApiModel.LATITUD, locacion.getLatitud());
                    datos.put(ManejadorApiModel.LONGITUD, locacion.getLongitud());
                    datos.put(ManejadorApiModel.NOMBRE_USUARIO, dueno.getNombre());
                    datos.put(ManejadorApiModel.APELLIDO_USUARIO, dueno.getApellido());
                    datos.put(ManejadorApiModel.CELULAR, dueno.getCelular());
                    datos.put(ManejadorApiModel.CORREO, dueno.getCorreo());
                    datos.put(ManejadorApiModel.REG_ID, dueno.getGcmId());
                    datos.put(ManejadorApiModel.NOMBRE_MASCOTA, mascota.getNombre());

                    ManejadorGcm.instance().enviarNotificacion(datos);
                    LOG.info(String.format(SUCCESS_MSG, mascota.getNombre(), dueno.getGcmId()));

                    notification.setFecha(null);
                    notification.setGcmIdDenunciante(null);
                    notification.setGcmIdDueno(null);

                    return notification;
                }
                catch (GcmException ex) {
                    LOG.error(ex.getMessage());
                    LOG.error("GCM que falla: " + dueno.getGcmId());
                }
                catch (Base64Exception ex) {
                    LOG.error("No se puso generar la imagen");
                }
            }
        }

        return null;
    }
}
