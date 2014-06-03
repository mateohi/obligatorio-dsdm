package com.findme.app.controller.integration.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.findme.app.controller.integration.PetServiceClient;
import com.findme.app.model.Mascota;

public class PostPetTask  extends AsyncTask<Mascota, Void, String> {

	private Activity parent;

	public PostPetTask(Activity parent) {
		this.parent = parent;
	}

	@Override
	protected String doInBackground(Mascota... arg0) {
		Mascota mascota = arg0[0];
		String serviceResponse = PetServiceClient.instance().postPet(mascota);
		
		return serviceResponse;
	}

	@Override
	protected void onPostExecute(String serviceResponse) {
		if (serviceResponse.isEmpty()) {
			Toast.makeText(this.parent, "Mascota guardada", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this.parent, serviceResponse, Toast.LENGTH_SHORT).show();
		}
	}

}
