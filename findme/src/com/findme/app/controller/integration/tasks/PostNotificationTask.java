package com.findme.app.controller.integration.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.findme.app.controller.DatabaseHandler;
import com.findme.app.controller.api.Locacion;
import com.findme.app.controller.integration.NotificationServiceClient;
import com.findme.app.model.Notificacion;
import com.findme.app.utils.Base64Utils;
import com.findme.app.utils.ImageUtils;

public class PostNotificationTask extends AsyncTask<String, Void, Pair<String, Notificacion>> {

	private Activity parent;
	private ProgressDialog progress;

	public PostNotificationTask(Activity parent) {
		this.parent = parent;
	}

	@Override
	protected Pair<String, Notificacion> doInBackground(String... arg0) {
		String gcmId = arg0[0];
		String gcmIdDueno = arg0[1];
		Pair<String, Notificacion> serviceResponse = NotificationServiceClient.instance().notify(gcmId,gcmIdDueno, getLocacion());
		
		return serviceResponse;
	}

	@Override
	protected void onPostExecute(Pair<String, Notificacion> serviceResponse) {
		this.progress.dismiss();
		
		if (serviceResponse.first.isEmpty()) {
			Notificacion notificacion = serviceResponse.second;
			Context ctx = this.parent.getApplicationContext();
			String path = "N-" + serviceResponse.second.getNombreMascota();
			Bitmap image = ImageUtils.bytesToBitmap(Base64Utils.base64StringToBytes(notificacion.getFotoBase64()));
			
			try {
				ImageUtils.saveImageOnDevice(image, path, ctx);
			} catch (Exception e) {
				// No se guarda
			}
			
			notificacion.setFotoBase64(null);
			notificacion.setPathFoto(path);
			DatabaseHandler dh = new DatabaseHandler(ctx);
			dh.addNotificacionEnviada(notificacion);
			Toast.makeText(this.parent, "Notificacion enviada", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this.parent, serviceResponse.first, Toast.LENGTH_SHORT).show();
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
