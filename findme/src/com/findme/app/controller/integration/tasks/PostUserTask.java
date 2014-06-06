package com.findme.app.controller.integration.tasks;

import com.findme.app.controller.integration.UserServiceClient;
import com.findme.app.model.Usuario;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class PostUserTask extends AsyncTask<Usuario, Void, String> {

	private Activity parent;
	private ProgressDialog progress;

	public PostUserTask(Activity parent) {
		this.parent = parent;
	}

	@Override
	protected String doInBackground(Usuario... arg0) {
		Usuario usuario = arg0[0];
		String serviceResponse = UserServiceClient.instance().postUser(
				usuario);
		
		return serviceResponse;
	}

	@Override
	protected void onPostExecute(String serviceResponse) {
		this.progress.dismiss();
		
		if (serviceResponse.isEmpty()) {
			Toast.makeText(this.parent, "Usuario guardado", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this.parent, serviceResponse, Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onPreExecute() {
		createAndShowProgress();
	}

	private void createAndShowProgress() {
		this.progress = new ProgressDialog(this.parent);
		this.progress.setTitle("Actualizando usuario");
		this.progress.setMessage("Espere un momento ...");
		this.progress.setCancelable(false);
		this.progress.setIndeterminate(true);
		this.progress.show();
	}

}
