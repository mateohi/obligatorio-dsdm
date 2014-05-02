package com.findme.service.gcm;

public class GcmException extends Exception {

    private static final String MSG_ERROR = "Error al comunicarse con GCM";

    public GcmException() {
        super(MSG_ERROR);
    }

    public GcmException(String detalle) {
        super(MSG_ERROR + "\n" + detalle);
    }
}
