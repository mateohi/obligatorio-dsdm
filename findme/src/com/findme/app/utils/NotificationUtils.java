package com.findme.app.utils;

import java.io.FileNotFoundException;
import java.util.Date;

import com.findme.app.R;
import com.findme.app.controller.DatabaseHandler;
import com.findme.app.model.Notificacion;
import com.findme.app.views.MainActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

public class NotificationUtils {

	public static final String LATITUD = "latitud";
    public static final String LONGITUD = "longitud";
    public static final String NOMBRE_USUARIO = "nombreUsuario";
    public static final String NOMBRE_MASCOTA = "nombreMascota";
    public static final String APELLIDO_USUARIO = "apellidoUsuario";
    public static final String CELULAR = "celular";
    public static final String CORREO = "correo";
    public static final String REG_ID = "reg_id";
    
	private static final String PREFS_NAME = "FindMeConfig";
	private static final String SILENT = "silent";
	private static final String VIBRATE = "vibrate";
	private static final int NOTIFICATION_ID = 1;
    
	public static void saveBundleAsReceivedNotification(Bundle bundle, Context ctx) {
		Notificacion notificacion = bundleToNotification(bundle);
		sendNotification(notificacion, ctx);
		
		DatabaseHandler dh = new DatabaseHandler(ctx);
		dh.addNotificacionRecibida(notificacion);
	}
	
	private static Notificacion bundleToNotification(Bundle bundle) {
		String latitud = bundle.getString(LATITUD);
		String longitud = bundle.getString(LONGITUD);
		String nombreUsuario = bundle.getString(NOMBRE_USUARIO);
		String nombreMascota = bundle.getString(NOMBRE_MASCOTA);
		String apellidoUsuario = bundle.getString(APELLIDO_USUARIO);
		String celular = bundle.getString(CELULAR);
		String correo = bundle.getString(CORREO);

		Notificacion notificacion = new Notificacion();
		notificacion.setLatitud(latitud);
		notificacion.setLongitud(longitud);
		notificacion.setNombreUsuario(nombreUsuario);
		notificacion.setNombreMascota(nombreMascota);
		notificacion.setApellidoUsuario(apellidoUsuario);
		notificacion.setCelular(celular);
		notificacion.setCorreo(correo);
		notificacion.setFecha(new Date());
		
		return notificacion;
	}
	
	private static void sendNotification(Notificacion notificacion, Context ctx) {
		Bitmap icono = devolverIconoGrande(ctx);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				ctx).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Mascota encontrada!")
				.setContentText("Han encontrado a " + notificacion.getNombreMascota())
				.setLargeIcon(icono);

		Intent resultIntent = new Intent(ctx, MainActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
		stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addNextIntent(resultIntent);

		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(resultPendingIntent);
		
		Notification notification = builder.build();
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		
		NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID, notification);
		
		NotificationUtils.avisoNotificiacion(ctx.getApplicationContext());
	}
	
	private static Bitmap devolverIconoGrande(Context ctx) {
		
		try {
			DatabaseHandler dh = new DatabaseHandler(ctx);
			String path = dh.getMascota().getPathFoto();
			Bitmap fotoMascota = ImageUtils.getCircleBitmapFromDevice(path, ctx);
			return fotoMascota;
		} catch (FileNotFoundException e) {
			Bitmap iconoFindMe = BitmapFactory.decodeResource(ctx.getResources(),
					R.drawable.ic_launcher);
			return iconoFindMe;
		}
	}
	
	public static void avisoNotificiacion(Context ctx) {
		if (conVibrar(ctx)) {
			Vibrator vibrator = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(1500);
		}

		if (conSonido(ctx)) {
			Uri defaultRingtoneUri = RingtoneManager
					.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

			MediaPlayer mediaPlayer = new MediaPlayer();

			try {
				mediaPlayer.setDataSource(ctx, defaultRingtoneUri);
				mediaPlayer
						.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
				mediaPlayer.prepare();
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						mp.release();
					}
				});
				mediaPlayer.start();
			} catch (Exception e) {
				// No se ejecuta y punto...
			}
		}
	}

	private static boolean conSonido(Context ctx) {
		SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		return settings.getBoolean(SILENT, false);
	}

	private static boolean conVibrar(Context ctx) {
		SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);
		return settings.getBoolean(VIBRATE, false);
	}
}
