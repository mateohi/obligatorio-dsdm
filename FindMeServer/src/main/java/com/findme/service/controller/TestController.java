package com.findme.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String testService() {
        return "{\"status\":\"ok\"}";
    }
}
