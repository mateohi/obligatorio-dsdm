package com.findme.service.gcm.apimodel;

import java.util.Arrays;
import java.util.Map;

public class ManejadorApiModel {

    public static final String NOMBRE_USUARIO = "nombre_usuario";
    public static final String NOMBRE_MASCOTA = "nombre_mascota";
    public static final String APELLIDO_USUARIO = "apellido_usuario";
    public static final String CELULAR = "celular";
    public static final String CORREO = "correo";
    public static final String REG_ID = "reg_id";

    public static RequestBody mapToRequest(Map<String, String> mapa) {
        RequestBodyData requestData = new RequestBodyData();
        requestData.setNombreUsuario(mapa.get(NOMBRE_USUARIO));
        requestData.setNombreMascota(mapa.get(NOMBRE_MASCOTA));
        requestData.setApellidoUsuario(mapa.get(APELLIDO_USUARIO));
        requestData.setCelular(mapa.get(CELULAR));
        requestData.setCorreo(mapa.get(CORREO));

        RequestBody request = new RequestBody();
        request.setRegistration_ids(Arrays.asList(mapa.get(APELLIDO_USUARIO)));
        request.setData(requestData);

        return request;
    }
}
