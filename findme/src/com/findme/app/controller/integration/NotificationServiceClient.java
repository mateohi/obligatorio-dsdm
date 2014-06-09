package com.findme.app.controller.integration;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Pair;

import com.findme.app.controller.api.Locacion;
import com.findme.app.model.Notificacion;
import com.google.gson.Gson;

public class NotificationServiceClient {

	private static final String NOTIFICATIONS = "/notifications";
	private static final String GCM_ID = "?gcmId=";
	private static final String GCM_ID_DUENO = "&gcmIdDueno=";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";

	private static NotificationServiceClient instance;
	private HttpClient cliente;
	private Gson gson;

	private NotificationServiceClient() {
		this.cliente = new DefaultHttpClient();
		this.gson = new Gson();
	}

	public static NotificationServiceClient instance() {
		if (instance == null) {
			instance = new NotificationServiceClient();
		}

		return instance;
	}

	public Pair<String, Notificacion> notify(String gcmId, String gcmIdDueno, Locacion locacion) {
		try {
			String jsonBody = gson.toJson(locacion);
			StringEntity entity = new StringEntity(jsonBody);

			HttpPost post = new HttpPost(ServiceConstants.SERVICE_URL
					+ NOTIFICATIONS + GCM_ID + gcmId + GCM_ID_DUENO + gcmIdDueno);
			post.addHeader(CONTENT_TYPE, APPLICATION_JSON);
			post.setEntity(entity);

			HttpResponse response = cliente.execute(post);
			int status = response.getStatusLine().getStatusCode();

			if (status == HttpStatus.SC_OK) {
				Notificacion notificacion = responseToNotificacion(response);
				return Pair.create("", notificacion);
			} else {
				return Pair.create("Server responde: " + status, null);
			}
		} catch (ClientProtocolException ex) {
			return Pair.create(ex.getMessage(), null);
		} catch (IOException ex) {
			return Pair.create("No se pudo conectar con el servidor", null);
		} catch (Exception ex) {
			return Pair.create("Error inesperado: " + ex.getMessage(), null);
		}
	}

	private Notificacion responseToNotificacion(HttpResponse response) {
		HttpEntity entity = response.getEntity();

		try {
			String data = EntityUtils.toString(entity);
			Notificacion notificacion = this.gson.fromJson(data,
					Notificacion.class);
			notificacion.setFecha(new Date());
			return notificacion;
		} catch (Exception ex) {
			return null;
		}
	}
}
