package com.findme.service.controller;

import com.findme.service.dataaccess.UsuarioDao;
import com.findme.service.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UsuarioDao usuarioDao;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void guardarUsuario(@RequestBody(required = false) Usuario usuario) {
        this.usuarioDao.addUsuario(usuario);
    }
}
