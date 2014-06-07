package com.findme.service.gcm;

import com.findme.service.gcm.apimodel.ManejadorApiModel;
import com.findme.service.gcm.apimodel.RequestBody;
import com.findme.service.gcm.apimodel.ResponseGcm;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ManejadorGcm {

    private static final String URL_POST_MESSAGE = "https://android.googleapis.com/gcm/send";
    private static final String AUTH = "Authorization";
    private static final String AUTH_KEY = "key=AIzaSyB8naiF_-N-4eAZvQdgZRPzTev0nIie_js";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String TYPE_JSON = "application/json";
    private static ManejadorGcm instance;
    private HttpClient client;
    private Gson gson;

    private ManejadorGcm() {
        this.client = new DefaultHttpClient();
        this.gson = new Gson();
    }

    public static ManejadorGcm instance() {
        if (instance == null) {
            instance = new ManejadorGcm();
        }
        return instance;
    }

    public void enviarNotificacion(Map<String, String> data) throws GcmException {
        RequestBody body = ManejadorApiModel.mapToRequest(data);
        enviarNotificacion(body);
    }

    private void enviarNotificacion(RequestBody body) throws GcmException {
        try {
            HttpPost post = crearPost(body);
            HttpResponse response = client.execute(post);

            ResponseGcm responseGcm = devolverRespuestaGcm(response);
            int status = response.getStatusLine().getStatusCode();

            manejarStatus(status, responseGcm);
        }
        catch (HttpException ex) {
            throw new GcmException("Error al intentar enviar a GCM");
        }
        catch (IOException ex) {
            throw new GcmException("Error al intentar enviar a GCM");
        }
        catch (URISyntaxException ex) {
            throw new GcmException("Error en la URI de GCM");
        }
    }

    private HttpPost crearPost(RequestBody body) throws UnsupportedEncodingException, URISyntaxException {
        String jsonMensaje = gson.toJson(body);
        StringEntity entity = new StringEntity(jsonMensaje);

        HttpPost post = new HttpPost(URL_POST_MESSAGE);
        post.addHeader(AUTH, AUTH_KEY);
        post.addHeader(CONTENT_TYPE, TYPE_JSON);
        post.setEntity(entity);

        return post;
    }

    private ResponseGcm devolverRespuestaGcm(HttpResponse response) throws IOException {
        HttpEntity responseEntity = response.getEntity();
        String respuestaString = EntityUtils.toString(responseEntity);

        return gson.fromJson(respuestaString, ResponseGcm.class);
    }

    private void manejarStatus(int status, ResponseGcm response) throws GcmException {
        if (status == HttpStatus.SC_OK) {
            if (response.envioFallido()) {
                throw new GcmException("Registration ID no valido. Error de Gcm: " + response.getResultsString());
            }
        }
        else {
            if (status == HttpStatus.SC_NOT_FOUND) {
                throw new GcmException("Body o json no valido");
            }
            else if (status == HttpStatus.SC_UNAUTHORIZED) {
                throw new GcmException("API key no valido");
            }
            else if (status / 100 == 5) {
                throw new GcmException("Error interno en GCM");
            }
            else {
                throw new GcmException();
            }
        }
    }
}
