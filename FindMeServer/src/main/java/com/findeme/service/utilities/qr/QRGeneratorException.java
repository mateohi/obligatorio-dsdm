package com.findeme.service.utilities.qr;

public class QRGeneratorException extends Exception {

    private static final String MSG_ERROR = "Error al generar el QR";
    
    public QRGeneratorException() {
        super(MSG_ERROR);
    }
}
