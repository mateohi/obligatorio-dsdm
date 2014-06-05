package com.findme.app.controller.integration.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.findme.app.controller.integration.NotificationServiceClient;

public class PostNotificationTask extends AsyncTask<Object, Void, String> {

	private Activity parent;

	public PostNotificationTask(Activity parent) {
		this.parent = parent;
	}

	@Override
	protected String doInBackground(Object... arg0) {
		String gcmId = (String) arg0[0];
		String serviceResponse = NotificationServiceClient.instance().notify(gcmId);
		
		return serviceResponse;
	}

	@Override
	protected void onPostExecute(String serviceResponse) {
		if (serviceResponse.isEmpty()) {
			Toast.makeText(this.parent, "Notificacion enviada", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this.parent, serviceResponse, Toast.LENGTH_SHORT).show();
		}
	}
}
