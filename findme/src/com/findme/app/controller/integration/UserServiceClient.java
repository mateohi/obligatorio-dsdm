package com.findme.app.controller.integration;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.findme.app.model.Usuario;
import com.google.gson.Gson;

public class UserServiceClient {

	private static final String USERS = "/users";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";

	private static UserServiceClient instance;
	private HttpClient cliente;
	private Gson gson;

	private UserServiceClient() {
		this.cliente = new DefaultHttpClient();
		this.gson = new Gson();
	}
	
	public static UserServiceClient instance() {
		if (instance == null) {
			instance = new UserServiceClient();
		}
		return instance;
	}
	
	public String postUser(Usuario usuario) {
		try {
			String jsonBody = gson.toJson(usuario);
	        StringEntity entity = new StringEntity(jsonBody);

	        HttpPost post = new HttpPost(ServiceConstants.SERVICE_URL + USERS);
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
}
