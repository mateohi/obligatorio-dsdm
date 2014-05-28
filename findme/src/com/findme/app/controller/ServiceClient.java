package com.findme.app.controller;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

public class ServiceClient {

	//private static final String SERVICE_URL = "http://mateohi.noip.me";
	private static final String SERVICE_URL = "http://localhost:8080";

	private HttpClient cliente;
	private Gson gson;

	public ServiceClient() {
		this.cliente = new DefaultHttpClient();
		this.gson = new Gson();
	}
}
