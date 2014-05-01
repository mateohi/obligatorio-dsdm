package com.findeme.service.utilities.mail;

public class MailGeneratorException extends Exception {

    private static final String MSG_ERROR = "Error al generar el mail";

    public MailGeneratorException() {
        super(MSG_ERROR);
    }
}
