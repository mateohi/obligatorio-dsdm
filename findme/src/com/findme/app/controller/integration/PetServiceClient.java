package com.findme.app.controller.integration;

import com.findme.app.model.Mascota;

public class PetServiceClient {

	private static PetServiceClient instance;
	
	private PetServiceClient() {
		
	}
	
	public static PetServiceClient instance() {
		if (instance == null) {
			instance = new PetServiceClient();
		}
		
		return instance;
	}
	
	public String postPet(Mascota mascota) {
		return "";
	}
}
