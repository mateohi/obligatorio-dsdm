package com.findme.app.controller;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.findme.model.Usuario;
import com.google.gson.Gson;

public class ServiceClient {

	//private static final String SERVICE_URL = "http://mateohi.noip.me";
	private static final String SERVICE_URL = "http://localhost:8080";
	private static final String ADD_USER = "/users";

	private HttpClient cliente;
	private Gson gson;

	public ServiceClient() {
		this.cliente = new DefaultHttpClient();
		this.gson = new Gson();
	}
	
	public String postUser(Usuario usuario) {
		try {
			String jsonBody = gson.toJson(usuario);
	        StringEntity entity = new StringEntity(jsonBody);

	        HttpPost post = new HttpPost(SERVICE_URL + ADD_USER);
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
			return ex.getMessage();
		}
		catch (Exception ex) {
			return "ERROR inesperado: " + ex.getMessage();
		}
	}
}
