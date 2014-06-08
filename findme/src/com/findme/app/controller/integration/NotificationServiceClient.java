package com.findme.app.controller.integration;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.findme.app.controller.api.Locacion;
import com.findme.app.controller.integration.tasks.ServiceConstants;
import com.google.gson.Gson;

public class NotificationServiceClient {

	private static final String NOTIFICATIONS = "/notifications";
	private static final String GCM_ID = "?gcmId=";
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
	
	public String notify(String gcmId, Locacion locacion) {
		try {
			String jsonBody = gson.toJson(locacion);
			StringEntity entity = new StringEntity(jsonBody);
			
			HttpPost post = new HttpPost(ServiceConstants.SERVICE_URL + NOTIFICATIONS + GCM_ID + gcmId);
			post.addHeader(CONTENT_TYPE, APPLICATION_JSON);
			post.setEntity(entity);

			HttpResponse response = cliente.execute(post);
			int status = response.getStatusLine().getStatusCode();

			if (status == HttpStatus.SC_OK) {
				return "";
			} 
			else {
				return "Server responde: " + status;
			}
		} 
		catch (ClientProtocolException ex) {
			return ex.getMessage();
		} 
		catch (IOException ex) {
			return "No se pudo conectar con el servidor";
		} 
		catch (Exception ex) {
			return "Error inesperado: " + ex.getMessage();
		}
	}
}
