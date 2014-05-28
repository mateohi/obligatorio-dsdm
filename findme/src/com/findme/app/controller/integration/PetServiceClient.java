package com.findme.app.controller.integration;

public class PetServiceClient {

	private PetServiceClient instance;
	
	private PetServiceClient() {
		
	}
	
	public PetServiceClient instance() {
		if (this.instance == null) {
			this.instance = new PetServiceClient();
		}
		
		return this.instance;
	}
}
