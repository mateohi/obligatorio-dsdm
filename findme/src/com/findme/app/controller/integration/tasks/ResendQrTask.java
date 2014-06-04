package com.findme.app.controller.integration.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.findme.app.controller.integration.PetServiceClient;

public class ResendQrTask extends AsyncTask<String, Void, String> {

	private Activity parent;

	public ResendQrTask(Activity parent) {
		this.parent = parent;
	}

	@Override
	protected String doInBackground(String... arg0) {
		String gcmId = arg0[0];
		String serviceResponse = PetServiceClient.instance().resendQR(gcmId);
		
		return serviceResponse;
	}

	@Override
	protected void onPostExecute(String serviceResponse) {
		if (serviceResponse.isEmpty()) {
			Toast.makeText(this.parent, "QR Reenviado", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this.parent, serviceResponse, Toast.LENGTH_SHORT).show();
		}
	}
}
