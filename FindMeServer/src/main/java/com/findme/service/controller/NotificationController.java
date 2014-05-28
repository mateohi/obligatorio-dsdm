package com.findme.service.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/notifications")
public class NotificationController {

    private static final Logger LOG = Logger.getLogger(NotificationController.class);

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void notificar(@RequestParam("idMascota") long idMascota,
            @RequestParam("idDenunciante") long idDenunciante) {
        LOG.info("Nueva notificacion");
    }
}
