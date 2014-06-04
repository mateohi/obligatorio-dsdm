package com.findme.service.controller;

import com.findme.service.dataaccess.UsuarioDao;
import com.findme.service.model.Usuario;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class);
    @Autowired
    private UsuarioDao usuarioDao;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void guardarUsuario(@RequestBody(required = false) Usuario usuario) {
        Usuario usuarioAnterior = this.usuarioDao.getUsuarioByGcmId(usuario.getGcmId());

        if (usuarioAnterior == null) {
            LOG.info("Nuevo usuario");
            usuario.setId(null);
            this.usuarioDao.addUsuario(usuario);
        }
        else {
            LOG.info("Actualizando usuario");
            usuario.setId(usuarioAnterior.getId());
            this.usuarioDao.addUsuario(usuario);
        }
    }
}
