package com.findme.service.utilities.base64;

public class Base64Exception extends Exception {

    private static final String MSG_ERROR = "Error al codificar/decodificar en base 64";
    public static final String CODIFICAR = "Error al codificar en base 64";
    public static final String DECODIFICAR = "Error al decodificar en base 64";

    public Base64Exception() {
        super(MSG_ERROR);
    }
    
    public Base64Exception(String msg) {
        super(msg);
    }
}
