package com.findme.service.controller;

import com.findme.service.model.Mascota;
import com.findme.service.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/findme")
public class FindMeController {

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/pet", params = "idDueno")
    public void guardarMascota(@RequestBody(required = false) Mascota mascota,
            @RequestParam("idDueno") long idDueno) {
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public void guardarUsuario(@RequestBody(required = false) Usuario usuario) {
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/qr", params = "idMascota")
    public void reenviarQr(@RequestParam("idMascota") long idMascota) {
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/notification",
            params = {"idMascota", "idDenunciante"})
    public void notificar(@RequestParam("idDueno") long idMascota,
            @RequestParam("idDenunciante") long idDenunciante) {
    }
}
