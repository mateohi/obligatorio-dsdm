package com.findme.service;

import com.findme.service.gcm.apimodel.RequestBody;
import com.findme.service.gcm.apimodel.RequestBodyData;
import com.findme.service.gcm.apimodel.ResponseGcm;
import com.google.gson.Gson;
import java.util.Arrays;

public class TestGcm {

    public static void main(String[] args) {
        Gson gson = new Gson();

        RequestBody m = mensajePrueba();
        System.out.println(gson.toJson(m));

        String mockResponse = "{ \"multicast_id\": 108,\n"
                + "  \"success\": 1,\n"
                + "  \"failure\": 0,\n"
                + "  \"canonical_ids\": 0,\n"
                + "  \"results\": [\n"
                + "    { \"message_id\": \"1:08\" }\n"
                + "  ]\n"
                + "}";
        ResponseGcm resp = gson.fromJson(mockResponse, ResponseGcm.class);
        System.out.println(resp.envioFallido());
    }

    public static RequestBody mensajePrueba() {
        RequestBodyData d = new RequestBodyData();
        d.setApellidoUsuario("apellidoUsu");
        d.setCelular("celu");
        d.setCorreo("correo");
        d.setNombreMascota("mascota");
        d.setNombreUsuario("usuario");

        RequestBody m = new RequestBody();
        m.setRegistration_ids(Arrays.asList("regId"));
        m.setData(d);

        return m;
    }
}
