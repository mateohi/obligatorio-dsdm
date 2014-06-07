package com.findme.app.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.findme.R;
import com.example.findme.R.id;
import com.findme.app.model.Notificacion;
import com.findme.app.model.Usuario;

public class FragmentDetailedNotification extends Fragment {

	private View parentView;
	private Notificacion notificacion;
	private boolean isReceivedNotification;

	public FragmentDetailedNotification(){}
	
//	public FragmentDetailedNotification(Notificacion pNotificacion,
//			boolean pIsReceivedNotification) {
//		this.notificacion = pNotificacion;
//		this.isReceivedNotification = pIsReceivedNotification;
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_detailed_notification,
				container, false);
		parentView = view;
		cargarDatos();
		return view;
	}

	public void cargarDatos() {
		if (notificacion != null) {
			Usuario usuario;
			if (isReceivedNotification) {
				usuario = notificacion.getUsarioInformante();
			} else {
				usuario = notificacion.getUsuarioDueno();
			}

			// ((ImageView))
			// parentView.findViewById(id.my_pet_image_notification).setIm;
			((EditText) parentView
					.findViewById(id.my_pet_profile_name_notification))
					.setText(notificacion.getMascota().getNombre());
			((EditText) parentView
					.findViewById(id.detailed_notification_profile_name))
					.setText(usuario.getNombre());
			((EditText) parentView
					.findViewById(id.detailed_notification_last_name))
					.setText(usuario.getApellido());
			((EditText) parentView.findViewById(id.detailed_notification_phone))
					.setText(usuario.getCelular());
			((EditText) parentView.findViewById(id.detailed_notification_email))
					.setText(usuario.getCorreo());
		}
	}
}
