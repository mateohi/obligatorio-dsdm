package com.findme.app.controller.integration.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.widget.Toast;

import com.findme.app.controller.api.Locacion;
import com.findme.app.controller.integration.NotificationServiceClient;

public class PostNotificationTask extends AsyncTask<Object, Void, String> {

	private Activity parent;
	private ProgressDialog progress;

	public PostNotificationTask(Activity parent) {
		this.parent = parent;
	}

	@Override
	protected String doInBackground(Object... arg0) {
		String gcmId = (String) arg0[0];
		String serviceResponse = NotificationServiceClient.instance().notify(gcmId, getLocacion());
		
		return serviceResponse;
	}

	@Override
	protected void onPostExecute(String serviceResponse) {
		this.progress.dismiss();
		
		if (serviceResponse.isEmpty()) {
			Toast.makeText(this.parent, "Notificacion enviada", Toast.LENGTH_SHORT).show();
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
		this.progress.setTitle("Enviando notificacion");
		this.progress.setMessage("Espere un momento ...");
		this.progress.setCancelable(false);
		this.progress.setIndeterminate(true);
		this.progress.show();
	}
	
	private Locacion getLocacion() {
		LocationManager locationManager = (LocationManager) this.parent.getSystemService(Context.LOCATION_SERVICE);
		String locationProvider = LocationManager.NETWORK_PROVIDER; //.GPS_PROVIDER;

		Location location = locationManager.getLastKnownLocation(locationProvider);
		String latitud = String.valueOf(location.getLatitude());
		String longitud = String.valueOf(location.getLongitude());
		
		Locacion locacion = new Locacion();
		locacion.setLatitud(latitud);
		locacion.setLongitud(longitud);
		
		return locacion;
	}
}
