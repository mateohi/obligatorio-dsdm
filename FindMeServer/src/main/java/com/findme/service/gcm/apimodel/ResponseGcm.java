package com.findme.service.gcm.apimodel;

import java.util.List;

public class ResponseGcm {

    private int success;
    private int failure;
    private List<Result> results;

    public ResponseGcm() {
    }

    public int getSuccess() {
        return success;
    }

    public int getFailure() {
        return failure;
    }

    public boolean envioFallido() {
        return this.failure != 0;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getResultsString() {
        StringBuilder results = new StringBuilder();
        for (Result result : getResults()) {
            if (results.length() > 0) {
                results.append(", ");
            }
            results.append(result.getError());
        }
        return results.toString();
    }
}
