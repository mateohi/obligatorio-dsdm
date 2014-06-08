package com.findme.app.utils;

import com.findme.app.model.Notificacion;

import android.os.Bundle;

public class NotificationUtils {

	public static final String LATITUD = "latitud";
    public static final String LONGITUD = "longitud";
    public static final String NOMBRE_USUARIO = "nombreUsuario";
    public static final String NOMBRE_MASCOTA = "nombreMascota";
    public static final String APELLIDO_USUARIO = "apellidoUsuario";
    public static final String CELULAR = "celular";
    public static final String CORREO = "correo";
    public static final String REG_ID = "reg_id";
    
	public static Notificacion bundleToNotification(Bundle bundle) {
		Notificacion notificacion = new Notificacion();
		String latitud = bundle.getString(LATITUD);
		String longitud = bundle.getString(LONGITUD);
		String nombreUsuario = bundle.getString(NOMBRE_USUARIO);
		String nombreMascota = bundle.getString(NOMBRE_MASCOTA);
		String apellidoUsuario = bundle.getString(APELLIDO_USUARIO);
		String celular = bundle.getString(CELULAR);
		String correo = bundle.getString(CORREO);

		return notificacion;
	}
}
