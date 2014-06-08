package com.findme.app.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findme.R;
import com.example.findme.R.id;
import com.findme.app.model.Notificacion;

public class FragmentDetailedNotification extends Fragment {

	private View parentView;
	private Notificacion notificacion;

	public FragmentDetailedNotification(Notificacion pNotificacion) {
		this.notificacion = pNotificacion;
	}

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

			// ((ImageView))
			// parentView.findViewById(id.my_pet_image_notification).setIm;
			((TextView) parentView
					.findViewById(id.my_pet_profile_name_notification))
					.setText(notificacion.getNombreMascota());
			((EditText) parentView
					.findViewById(id.detailed_notification_profile_name))
					.setText(notificacion.getNombreUsuario());
			((EditText) parentView
					.findViewById(id.detailed_notification_last_name))
					.setText(notificacion.getApellidoUsuario());
			((EditText) parentView.findViewById(id.detailed_notification_phone))
					.setText(notificacion.getCelular());
			((EditText) parentView.findViewById(id.detailed_notification_email))
					.setText(notificacion.getCorreo());
		
			((EditText) parentView
					.findViewById(id.detailed_notification_profile_name)).setKeyListener(null);
			((EditText) parentView
					.findViewById(id.detailed_notification_last_name)).setKeyListener(null);
			((EditText) parentView
					.findViewById(id.detailed_notification_phone)).setKeyListener(null);			
			((EditText) parentView
					.findViewById(id.detailed_notification_email)).setKeyListener(null);	
					
		}
	}
}
