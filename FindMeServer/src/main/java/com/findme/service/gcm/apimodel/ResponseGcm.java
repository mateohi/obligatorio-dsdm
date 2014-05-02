package com.findme.service.gcm.apimodel;

public class ResponseGcm {

    private int success;
    private int failure;

    public ResponseGcm() {
    }

    public int getSuccess() {
        return success;
    }

    public int getFailure() {
        return failure;
    }

    public boolean envioExitoso() {
        return success > 0;
    }
}
