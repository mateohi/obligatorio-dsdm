package com.findme.app.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.findme.R;
import com.example.findme.R.id;
import com.findme.app.controller.DatabaseHandler;
import com.findme.app.model.Notificacion;

public class FragmentDetailedNotification extends Fragment {

	private View parentView;
	private Notificacion notificacion;
	private boolean isReceivedNotification;

	public FragmentDetailedNotification() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		DatabaseHandler dh = new DatabaseHandler(getActivity()
				.getApplicationContext());
		isReceivedNotification = getArguments()
				.getInt("isReceivedNotification") == 1 ? true : false;
		if (isReceivedNotification) {
			notificacion = dh.getNotificacionRecibida(getArguments().getInt(
					"notificationId"));
		} else {
			notificacion = dh.getNotificacionEnviada(getArguments().getInt(
					"notificationId"));
		}
		
		View view = inflater.inflate(R.layout.fragment_detailed_notification,
				container, false);
		parentView = view;
		cargarDatos();
		setUpView();
		setButtonListener();
		return view;
	}

	private void setButtonListener() {
		Button buttonLocation = (Button) parentView
				.findViewById(R.id.button_location);
		buttonLocation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				if (notificacion != null) {
					Uri geoLocationUri = Uri.parse("geo:<lat>,<long>?q="
							+ notificacion.getLatitud() + ","
							+ notificacion.getLongitud());
					intent.setData(geoLocationUri);
				}

				if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
					startActivity(intent);
				}

			}
		});

	}

	private void setUpView() {
		if (!isReceivedNotification) {
			((TextView) parentView
					.findViewById(id.detailed_notification_ubication))
					.setVisibility(View.GONE);
			((View) parentView.findViewById(id.view_location))
					.setVisibility(View.GONE);
			((Button) parentView.findViewById(id.button_location))
					.setVisibility(View.GONE);
		}
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
					.findViewById(id.detailed_notification_profile_name))
					.setKeyListener(null);
			((EditText) parentView
					.findViewById(id.detailed_notification_last_name))
					.setKeyListener(null);
			((EditText) parentView.findViewById(id.detailed_notification_phone))
					.setKeyListener(null);
			((EditText) parentView.findViewById(id.detailed_notification_email))
					.setKeyListener(null);

		}
	}
}
