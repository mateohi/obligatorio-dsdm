package com.findme.app.controller.integration;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.findme.app.controller.integration.tasks.ServiceConstants;
import com.findme.app.model.Mascota;
import com.google.gson.Gson;

public class PetServiceClient {

	private static final String PETS = "/pets";
	private static final String GCM_ID = "?gcmId=";
	private static final String RESEND_QR = "/%s/qr";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";

	private static PetServiceClient instance;
	private HttpClient cliente;
	private Gson gson;

	private PetServiceClient() {
		this.cliente = new DefaultHttpClient();
		this.gson = new Gson();
	}

	public static PetServiceClient instance() {
		if (instance == null) {
			instance = new PetServiceClient();
		}

		return instance;
	}

	public String postPet(Mascota mascota, String gcmId) {
		try {
			String jsonBody = gson.toJson(mascota);
			StringEntity entity = new StringEntity(jsonBody);

			HttpPost post = new HttpPost(ServiceConstants.SERVICE_URL + PETS + GCM_ID + gcmId);
			post.setEntity(entity);
			post.addHeader(CONTENT_TYPE, APPLICATION_JSON);

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

	public String resendQR(String gcmId) {
		try {
			HttpGet get = new HttpGet(ServiceConstants.SERVICE_URL + PETS +  String.format(RESEND_QR, gcmId));
			get.addHeader(CONTENT_TYPE, APPLICATION_JSON);

			HttpResponse response = cliente.execute(get);
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
