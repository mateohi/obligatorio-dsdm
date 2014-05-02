package com.findme.service.gcm.apimodel;

import java.util.List;

public class RequestBody {

    private List<String> registration_ids;
    private RequestBodyData data;

    public RequestBody() {
    }

    public List<String> getRegistration_ids() {
        return registration_ids;
    }

    public void setRegistration_ids(List<String> registration_ids) {
        this.registration_ids = registration_ids;
    }

    public RequestBodyData getData() {
        return data;
    }

    public void setData(RequestBodyData data) {
        this.data = data;
    }
}
